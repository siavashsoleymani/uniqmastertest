package com.uniqmaster.uniqmastertest.service;

public interface EmailService {
  void sendEmailWithSubjectWithMessageTo(String subject, String message, String recipient);
}
