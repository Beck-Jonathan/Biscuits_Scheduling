package com.beck.beck_demos.schedule_app.data_fakes;

import com.beck.beck_demos.schedule_app.iData.iEmailService;
import com.beck.beck_demos.schedule_app.models.Event;
import jakarta.mail.MessagingException;

import java.util.List;

public class EmailServiceFake implements iEmailService {
  @Override
  public void sendEventSchedule(String recipientEmail, String subject, List<Event> events) throws MessagingException {
    return;
  }
}
