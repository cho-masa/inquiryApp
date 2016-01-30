package com.example.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import bean.InquiryForm;

import com.example.Constant;

/**
 * メール送信ビジネスロジッククラス.
 * [JavaMail]を利用.
 * メリット：送信者名、宛先者名を指定できる
 * 
 * <pre>
 * spring-reference
 *   url:  http://docs.spring.io/spring/docs/4.2.4.RELEASE/spring-framework-reference/htmlsingle/#mail-usage-mime
 * 
 * sample code
 *   url:  http://qiita.com/rubytomato@github/items/b106ff8011bcad60bce2
 *   property
 *     mail.smtp.user=
 *     mail.smtp.host=
 *     mail.smtp.port=
 *     mail.smtp.from=
 *     mail.smtp.connectiontimeout=0    # コネクション確立までのタイムアウト時間(ミリ秒)
 *     mail.smtp.timeout=0              # SMTPサーバとの通信(read)のタイムアウト時間(ミリ秒)
 *     mail.smtp.writetimeout=0         # ソケットの書き込みのタイムアウト時間(ミリ秒)
 *     mail.smtp.auth=false             # authコマンドでのユーザー認証を行うか
 *     mail.smtp.ssl.enable=false       #
 *     mail.smtp.starttls.enable=false  #
 *     mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory   # ssl
 *     mail.smtp.socketFactory.fallback=false                         # ssl
 *     mail.smtp.socketFactory.port=                                  # ssl
 * </pre>
 */
@Service
public class Email1Service {
  
  @Autowired
  private VelocityUtils velocityUtils;

  @Autowired
  private JavaMailSender mailSender;

  public void sendJavaMail(InquiryForm form, String templateLocation) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {

      public void prepare(MimeMessage mimeMessage) throws Exception {
        
        // メールヘッダー
        mimeMessage.setFrom(new InternetAddress("fromuser@test.co.jp", "送信ユーザA", "ISO-2022-JP"));
        mimeMessage.setRecipient(Message.RecipientType.TO,
            new InternetAddress("touser@test.co.jp", "宛先ユーザB", "ISO-2022-JP"));
        mimeMessage.setSubject("test mail");

        // メール本文
        Constant constant = Constant.getInstance();
        Map<String, Object> model = new HashMap<>();
        model.put("name", form.getName());
        model.put("type", constant.type_map.get(form.getType()));
        model.put("content", form.getContent());
        mimeMessage.setText(velocityUtils.merge(templateLocation, model));
      }
    };
    // 送信
    this.mailSender.send(preparator);
  }
}
