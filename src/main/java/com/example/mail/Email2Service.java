package com.example.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.Constant;

import bean.InquiryForm;

/**
 * メール送信ビジネスロジッククラス.
 * [SpringBoot Starter mail]を利用.
 * メリット：実装が簡単
 * 
 * <pre>
 * spring-reference
 *   url:  http://docs.spring.io/spring/docs/4.2.4.RELEASE/spring-framework-reference/htmlsingle/#mail-usage-simple
 * 
 * sample code
 *   url:  http://qiita.com/rubytomato@github/items/b106ff8011bcad60bce2
 *   property(application.yml)
 *     spring:
 *     # Email (MailProperties)
 *       mail:
 *         default-encoding: UTF-8
 *         protocol: smtp
 *         #host: localhost
 *         #port: 2525
 *         host: smtp.gmail.com
 *         port: 587
 *         jndi-name: mail/Session
 *         password: ********** # Google App password
 *         username: *****.*****.*****@gmail.com # Google account mail address
 *         properties:
 *           mail:
 *             smtp:
 *               auth: true
 *               starttls:
 *                 #enable: false
 *                 enable: true
 *               socketFactory:
 *                 #port: 2525
 *                 port: 587
 *                 class: javax.net.ssl.SSLSocketFactory
 *                 fallback: false
 *             debug: true
 *         test-connection: false
 * </pre>
 */
@Service
public class Email2Service {
  
  @Autowired
  private VelocityUtils velocityUtils;

  @Autowired
  private MailSender mailSender;

  // メール送信
  public void sendSimpleMail(InquiryForm form, String templateLocation) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    
    // メールヘッダー
    mailMessage.setFrom("fromuser@test.co.jp");
    mailMessage.setTo("touser@test.co.jp");
    mailMessage.setSubject("test mail");
    
    // メール本文
    Constant constant = Constant.getInstance();
    Map<String, Object> model = new HashMap<>();
    model.put("name", form.getName());
    model.put("type", constant.type_map.get(form.getType()));
    model.put("content", form.getContent());
    mailMessage.setText(velocityUtils.merge(templateLocation, model));
    
    mailSender.send(mailMessage);
  }
}
