package com.example.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

import bean.InquiryForm;

import com.example.Constant;

// 問い合わせメール用オブジェクト生成クラス
public class InquiryMailBuilder {

  private InquiryForm form;
  private VelocityUtils velocityUtils;
  private String templateLocation;
  
  public static InquiryMailBuilder build() {
    return new InquiryMailBuilder();
  }

  public InquiryMailBuilder setForm(InquiryForm form) {
    this.form = form;
    return this;
  }

  public InquiryMailBuilder setVelocityUtils(VelocityUtils velocityUtils) {
    this.velocityUtils = velocityUtils;
    return this;
  }

  public InquiryMailBuilder setTemplateLocation(String templateLocation) {
      this.templateLocation = templateLocation;
      return this;
  }

  public SimpleMailMessage create() {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("chonmage39@gmail.com");
    mailMessage.setTo("cohnmage39@gmail.com");
    mailMessage.setSubject("test mail");
    
    Constant constant = Constant.getInstance();
    Map<String, Object> model = new HashMap<>();
    model.put("name", form.getName());
    model.put("type", constant.type_map.get(form.getType()));
    model.put("content", form.getContent());
    mailMessage.setText(velocityUtils.merge(this.templateLocation, model));

    return mailMessage;
  }

}
