package com.example.mail;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
public class Email3Service {
  
  @Autowired
  private VelocityUtils velocityUtils;

  @Autowired
  private JavaMailSender mailSender;

  // メール送信
  public void sendSimpleMail(InquiryForm form, String templateLocation) {

    try {

      MimeMessage mail = mailSender.createMimeMessage();
      // メールヘッダー
        mail.setHeader("Content-Transfer-Encoding", "base64");
      MimeMessageHelper helper = new MimeMessageHelper(mail, false);
      helper.setReplyTo("fromuser@test.co.jp");
      helper.setFrom(new InternetAddress("fromuser@test.co.jp", "送信ユーザA", "ISO-2022-JP"));

      // TOは複数指定できるように対応する
      String strTo = "touser@test.co.jp";
      helper.setTo(strTo.split(","));

      // CCは複数指定できるように対応する
      String strCc = "ccuser1@test.co.jp ccuser2@test.co.jp";
      helper.setCc(strCc.split(","));

      // BCCは複数指定できるように対応する
      String strBcc = "bccuser1@test.co.jp bccuser2@test.co.jp";
      helper.setBcc(strBcc.split(","));
      
      helper.setSubject("test mail");

      // メール本文
      Constant constant = Constant.getInstance();
      Map<String, Object> model = new HashMap<>();
      model.put("name", form.getName());
      model.put("type", constant.type_map.get(form.getType()));
      model.put("content", form.getContent());
      helper.setText(velocityUtils.merge(templateLocation, model));

      mailSender.send(mail);

    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new RuntimeException("メール送信処理で失敗しました。", e);
    }
  }
}
