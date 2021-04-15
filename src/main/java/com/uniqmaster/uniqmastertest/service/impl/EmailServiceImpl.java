package com.uniqmaster.uniqmastertest.service.impl;

import com.uniqmaster.uniqmastertest.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  @Autowired
  public EmailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async("emailSenderThreadPool")
  @Override
  public void sendEmailWithSubjectWithMessageTo(String subject, String body, String recipient) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@uniqmaster.com");
    message.setTo(recipient);
    message.setSubject(subject);
    message.setText(body);
    mailSender.send(message);
  }
}
