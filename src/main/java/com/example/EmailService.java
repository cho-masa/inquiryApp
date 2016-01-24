package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

// ビジネスロジッククラス
@Service
public class EmailService {

  @Autowired
  private MailSender mailSender;

  public void sendSimpleMail(SimpleMailMessage mailMessage) {
    mailSender.send(mailMessage);
  }
}
