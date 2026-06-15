package com.beck.beck_demos.schedule_app.iData;

import com.beck.beck_demos.schedule_app.models.Event;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

public interface iEmailService {
  void sendEventSchedule(String recipientEmail, String subject, List<Event> events) throws MessagingException;

}
