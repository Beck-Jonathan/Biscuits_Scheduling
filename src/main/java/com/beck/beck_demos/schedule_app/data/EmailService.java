package com.beck.beck_demos.schedule_app.data;

import com.beck.beck_demos.schedule_app.iData.iEmailService;
import com.beck.beck_demos.schedule_app.models.Event;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailService implements iEmailService {

  private String username = "admin@jjbeck.us";

  @Override
  public void sendEventSchedule(String recipientEmail, String subject, List<Event> events) throws MessagingException {
    Properties props = new Properties();
    Dotenv dotenv = Dotenv.load();
    String smtpHost = dotenv.get("SMTP_HOST");
    String rackNerdUser = dotenv.get("RACKNERDUSER");
    String rackNerdPass = dotenv.get("RACKNERDPASS");
    username = dotenv.get("MAIL_USER");
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", smtpHost);
    props.put("mail.smtp.connectiontimeout", "5000");
    props.put("mail.smtp.timeout", "5000");
    props.put("mail.transport.protocol", "smtp");

    // Pass 'null' for the authenticator since we will handle it explicitly below
    Session session = Session.getInstance(props, null);

    // This now builds the newly formatted split-section HTML
    String htmlBody = buildEventTableHtml(events);

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
    message.setSubject(subject);
    message.setContent(htmlBody, "text/html; charset=utf-8");

    // Open connection and transmit explicitly via port 587 settings
    try (Transport transport = session.getTransport("smtp")) {
      // Load environment variables directly at the time of connection



      // Authenticate directly with the remote server here instead of a nested function
      transport.connect(smtpHost, rackNerdUser, rackNerdPass);
      transport.sendMessage(message, message.getAllRecipients());
    } catch (MessagingException e) {
      e.printStackTrace(); // Ensures failures are captured clearly in your local console output
      throw e; // Rethrow to preserve method contract signaling
    }
  }

  private String buildEventTableHtml(List<Event> events) {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // Day comparison parser to identify what items are today
    SimpleDateFormat dayCheckFormat = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = dayCheckFormat.format(new Date());

    List<Event> todaysEvents = new ArrayList<>();
    List<Event> upcomingWeekEvents = new ArrayList<>();

    // Process the composite list down into individual category collections
    if (events != null) {
      for (Event event : events) {
        if (event.getDate() != null && dayCheckFormat.format(event.getDate()).equals(todayStr)) {
          todaysEvents.add(event);
        } else {
          upcomingWeekEvents.add(event);
        }
      }
    }

    // Outer framing consistent with native browser viewing rules
    sb.append("<html><body style='margin: 0; padding: 20px; font-family: Arial, sans-serif; background-color: #f9f9f9; color: #333;'>");
    sb.append("<div style='max-width: 700px; margin: 0 auto; background-color: #ffffff; padding: 25px; border-radius: 6px; border: 1px solid #e0e0e0;'>");

    sb.append("<h2 style='color: #1976d2; margin-top: 0; margin-bottom: 20px; border-bottom: 2px solid #1976d2; padding-bottom: 8px;'>Upcoming Event Schedule</h2>");

    // --- SECTION 1: TODAY'S AGENDA ---
    sb.append("<h3 style='color: #333; font-size: 16px; margin-top: 25px; margin-bottom: 10px;'>📅 Today's Full Agenda</h3>");
    sb.append("<p style='font-size: 13px; color: #666; margin-top: -5px; margin-bottom: 15px;'>All scheduled events and opportunities occurring within the next 24 hours.</p>");

    if (todaysEvents.isEmpty()) {
      sb.append("<p style='padding: 15px; background-color: #f5f5f5; border-radius: 4px; font-style: italic; color: #777;'>No events scheduled for today.</p>");
    } else {
      appendEventTable(sb, todaysEvents, dateFormat);
    }

    // --- SECTION 2: THE WEEK AHEAD (GOING ONLY) ---
    sb.append("<h3 style='color: #333; font-size: 16px; margin-top: 35px; margin-bottom: 10px;'>✅ Your Week Ahead (Confirmed Only)</h3>");
    sb.append("<p style='font-size: 13px; color: #666; margin-top: -5px; margin-bottom: 15px;'>Looking forward: Only showing events for the next 7 days where your status is marked as 'Going'.</p>");

    if (upcomingWeekEvents.isEmpty()) {
      sb.append("<p style='padding: 15px; background-color: #f5f5f5; border-radius: 4px; font-style: italic; color: #777;'>No confirmed upcoming events scheduled for this week.</p>");
    } else {
      appendEventTable(sb, upcomingWeekEvents, dateFormat);
    }

    sb.append("</div>");
    sb.append("</body></html>");
    return sb.toString();
  }

  // Basic XSS/HTML wrapper defense for dynamic text blocks
  private String escapeHtml(String input) {
    if (input == null) return "";
    return input.replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&#39;"); // Fixed corrupt Chinese character literal bug here
  }

  private void appendEventTable(StringBuilder sb, List<Event> events, SimpleDateFormat dateFormat) {
    sb.append("<table style='width: 100%; border-collapse: collapse; font-family: Arial, sans-serif; font-size: 14px; margin-bottom: 15px;'>");

    // Table Header
    sb.append("<tr style='background-color: #f2f2f2; border-bottom: 2px solid #ddd;'>");
    sb.append("<th style='padding: 10px; text-align: left; font-size: 13px; color: #555;'>Event Name</th>");
    sb.append("<th style='padding: 10px; text-align: left; font-size: 13px; color: #555;'>Date/Time</th>");
    sb.append("<th style='padding: 10px; text-align: left; font-size: 13px; color: #555;'>Location</th>");
    sb.append("<th style='padding: 10px; text-align: left; font-size: 13px; color: #555;'>Duration</th>");
    sb.append("<th style='padding: 10px; text-align: left; font-size: 13px; color: #555;'>Status</th>");
    sb.append("<th style='padding: 10px; text-align: left; font-size: 13px; color: #555;'>Paid</th>");
    sb.append("</tr>");

    // Table Rows
    for (Event event : events) {
      String formattedDate = (event.getDate() != null) ? dateFormat.format(event.getDate()) : "N/A";

      sb.append("<tr style='border-bottom: 1px solid #eee;'>");
      sb.append("<td style='padding: 10px; font-weight: bold; color: #222;'>").append(escapeHtml(event.getName())).append("</td>");
      sb.append("<td style='padding: 10px; color: #444;'>").append(formattedDate).append("</td>");
      sb.append("<td style='padding: 10px; color: #444;'>").append(escapeHtml(event.getLocation())).append("</td>");
      sb.append("<td style='padding: 10px; color: #444;'>").append(event.getLength() != null ? event.getLength() : "0.0").append(" hrs</td>");

      // Color-coded status chip styling
      String statusColor = "#555";
      String decision = event.getDecision() != null ? event.getDecision() : "Pending";
      if ("Going".equalsIgnoreCase(decision) || "Yes".equalsIgnoreCase(decision)) statusColor = "#2e7d32";
      if ("Skipping".equalsIgnoreCase(decision) || "No".equalsIgnoreCase(decision)) statusColor = "#c62828";

      sb.append("<td style='padding: 10px; color: ").append(statusColor).append("; font-weight: bold;'>")
          .append(decision).append("</td>");
      sb.append("<td style='padding: 10px; color: #444;'>").append(event.getPaid()).append("</td>");
      sb.append("</tr>");
    }
    sb.append("</table>");
  }
}