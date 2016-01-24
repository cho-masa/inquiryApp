package com.example;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import bean.PrefInfo;

// 定数クラス(constant.ymlから読み込んだ値を保持）
@Component
public class Constant {

  private static final Constant instance = (Constant) new Yaml().load(Constant.class.getResourceAsStream("/constant.yml"));

  public static Constant getInstance() {
    return instance;
  }
  
  // 問い合わせ項目
  public Map<String, String> type_map;
  
  // 都道府県
  public Map<String, PrefInfo> pref_map;
}
