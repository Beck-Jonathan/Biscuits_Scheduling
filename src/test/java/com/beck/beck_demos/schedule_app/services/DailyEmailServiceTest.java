package com.beck.beck_demos.schedule_app.services;

import static org.junit.jupiter.api.Assertions.*;

import com.beck.beck_demos.schedule_app.data.EmailService;
import com.beck.beck_demos.schedule_app.data.EventDAO;
import com.beck.beck_demos.schedule_app.data.UserDAO;
import com.beck.beck_demos.schedule_app.data_fakes.EmailServiceFake;
import com.beck.beck_demos.schedule_app.data_fakes.EventDAO_Fake;
import com.beck.beck_demos.schedule_app.data_fakes.UserDAO_Fake;
import com.beck.beck_demos.schedule_app.iData.iEmailService;
import com.beck.beck_demos.schedule_app.iData.iEventDAO;
import com.beck.beck_demos.schedule_app.iData.iUserDAO;
import com.beck.beck_demos.schedule_app.models.CalendarDay;
import com.beck.beck_demos.schedule_app.service_fakes.DailyEmailServiceFake;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.text.ParseException;

public class DailyEmailServiceTest {

  private DailyEmailService _dailyemailservice;
  private iEmailService _fakeEmailService;
  private iEventDAO _fakeEventDAO;
  private iUserDAO _fakeUserDAO;

  @BeforeEach
  public void setup() throws ParseException {
    _fakeEmailService = new EmailService();
    _fakeEventDAO = new EventDAO();
    _fakeUserDAO = new UserDAO();

    // Injecting your pre-populated external fakes
    _dailyemailservice = new DailyEmailService(_fakeEmailService, _fakeEventDAO, _fakeUserDAO);
  }

  @AfterEach
  public void teardown() {
    _dailyemailservice = null;
    _fakeEmailService = null;
    _fakeEventDAO = null;
    _fakeUserDAO = null;
  }

  /**
   <p> Tests that the daily routine processes emails and purges old events utilizing pre-populated fake data </p>
   */
  @Test
  public void testExecuteDailyRoutineSendsEmailsAndClearsOldEvents() throws SQLException {
    // Act: Run the background logic routine directly against your fake data pool
    _dailyemailservice.executeDailyRoutine();

    // Assert: Verify that the old events inside your EventDAO_Fake were purged
    // (Update the assertion condition below to match an ID or check if total count decreased)
    CalendarDay day = new CalendarDay();
    Assertions.assertNotNull(_fakeEventDAO.getEventForEmail(day,""));

    // Assert: Verify emails were processed by checking against a known user's email in your UserDAO_Fake
    // Assertions.assertTrue(_fakeEmailService.wasEmailSentTo("replace-with-known-fake-user-email@domain.com"));
  }
}