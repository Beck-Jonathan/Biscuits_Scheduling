package com.beck.beck_demos.schedule_app.services;

import com.beck.beck_demos.schedule_app.data.EmailService;
import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.data.UserDAO;
import com.beck.beck_demos.schedule_app.iData.iEmailService;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.iData.iUserDAO;
import com.beck.beck_demos.schedule_app.iServices.iDailyEmailService;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.models.Event;
import com.beck.beck_demos.schedule_app.models.User;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.beck.beck_demos.shared.MyValidators.emailPattern;

@WebListener
public class DailyEmailService implements ServletContextListener , iDailyEmailService {

  private ScheduledExecutorService scheduler;
  private iEmailService emailService;
  private iEventDAO eventDAO;
  private iUserDAO userDAO;

  // Default constructor used by Tomcat
  public DailyEmailService() {
    this.emailService = new EmailService();
    this.eventDAO = new EventDAO();
    this.userDAO = new UserDAO();
  }

  // Package-private constructor used explicitly by your Test Suite
  DailyEmailService(iEmailService emailService, iEventDAO eventDAO, iUserDAO userDAO) {
    this.emailService = emailService;
    this.eventDAO = eventDAO;
    this.userDAO = userDAO;
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // Correctly using class fields instead of re-instantiating local variables
    scheduler = Executors.newSingleThreadScheduledExecutor();

    // Calculate how many seconds to wait until the target execution time
    long initialDelayInSeconds = calculateSecondsUntilSevenAM();
    System.out.println("DailyEmailService: Initial delay is " + initialDelayInSeconds + " seconds.");

    long periodInSeconds = TimeUnit.DAYS.toSeconds(1);

    scheduler.scheduleAtFixedRate(() -> {
      executeDailyRoutine();
    }, initialDelayInSeconds, periodInSeconds, TimeUnit.SECONDS);
  }

  /**
   * Extracted execution routine so that the unit test suite can invoke this
   * logic instantly on demand without interacting with the scheduler.
   */
  public void executeDailyRoutine() {
    try {
      String baseSubject = "Daily Event Schedule Update";

      List<User> allUsers = userDAO.getAllUsers();
      CalendarDay day = new CalendarDay();
      LocalDateTime now = LocalDateTime.now();
      day.setDay(now.getDayOfMonth());
      day.setMonth(now.getMonthValue());
      day.setYear(now.getYear());

      for (User u : allUsers){
        String recipient = u.getEmail();
        if (!emailPattern.matcher(recipient).matches() ) {
          //System.err.println("Skipping invalid email format for User ID: " + u.getUser_ID() + " (Value: " + recipient + ")");
          continue;
        }
        else {
          //System.out.println("Sending email for User ID: " + u.getUser_ID() + " (Value: " + recipient + ")");
        }
        if (recipient.toLowerCase().equals("eabeck7@gmail.com")) {
          continue;
        }
        recipient="jjbeck7@gmail.com";
        // Dynamically create subject per loop iteration to prevent accumulation bugs
        String specificSubject = baseSubject + " for " + recipient + ".";

        List<Event> currentEvents = eventDAO.getEventForEmail(day, u.getUser_ID());
        System.out.println(currentEvents.toString());
        emailService.sendEventSchedule(recipient, specificSubject, currentEvents);
      }
      eventDAO.clearOldEvents();

    } catch (Exception e) {
      System.err.println("Failed to execute automatic daily email routine.");
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdownNow(); // Frees the thread context safely when Tomcat stops
    }
  }

  private long calculateSecondsUntilSevenAM() {
    // 1. Enforce Chicago Timezone cleanly
    ZoneId localZone = ZoneId.of("America/Chicago");
    ZonedDateTime now = ZonedDateTime.now(localZone);

    // 2. Build target execution date explicitly tied to Chicago time
    ZonedDateTime nextRun = now.withHour(7).withMinute(0).withSecond(0).withNano(0);

    // 3. If 2:16 PM Chicago time has already passed today, target tomorrow
    if (now.isAfter(nextRun)) {
      nextRun = nextRun.plusDays(1);
    }
    // 4. Correctly computes absolute real-world duration between the two zones
    return Duration.between(now, nextRun).toSeconds();
  }

}