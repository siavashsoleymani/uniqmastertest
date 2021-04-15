package com.uniqmaster.uniqmastertest.job;

import com.uniqmaster.uniqmastertest.repository.EventRepository;
import com.uniqmaster.uniqmastertest.service.EmailService;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class ReminderEmailJob {

  private final EventRepository eventRepository;
  private final EmailService emailService;

  @Autowired
  public ReminderEmailJob(
      EventRepository eventRepository,
      EmailService emailService) {
    this.eventRepository = eventRepository;
    this.emailService = emailService;
  }

  @Scheduled(fixedDelay = 5000)
  public void sendEmailReminder() {
    Timestamp oneHourAgo = new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000));
    Timestamp now = new Timestamp(System.currentTimeMillis());
    eventRepository.findByStartBetween(oneHourAgo, now)
        .forEach(evt -> evt.getAudiences().forEach(adc -> emailService
            .sendEmailWithSubjectWithMessageTo("Reminder for your subscribed event.",
                "The event " + evt.getTopic() + "started soon! are you ready to learn new things?",
                adc.getEmail())));
  }
}
