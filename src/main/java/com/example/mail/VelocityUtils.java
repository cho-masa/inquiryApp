package com.example.mail;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class VelocityUtils {

  @Autowired
  private VelocityEngine velocityEngine;
  
  @Value("${spring.velocity.charset}")
  private String charset;
  
  // メールテンプレートの変数を置換
  public String merge(String templateLocation, Map<String, Object> model) {
    return VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, templateLocation, charset, model);
  }
}
