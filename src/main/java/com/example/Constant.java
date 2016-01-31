package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

// 定数クラス(constant.ymlから読み込んだ値を保持）
@Component
public class Constant {

  private static final Constant instance = (Constant) new Yaml().load(Constant.class.getResourceAsStream("/constant.yml"));

  public static Constant getInstance() {
    return instance;
  }
  
  // 問い合わせ項目
  public Map<String, String> type_map;

  // エリア情報
  public Map<String, List<String>> area_map;
  // エリア情報_日本語
  public Map<String, List<String>> area_map_ja;
  // エリア情報_韓国語
  public Map<String, List<String>> area_map_ko;
  // エリア情報_中国語
  public Map<String, List<String>> area_map_zh;
  // エリア情報_フランス語
  public Map<String, List<String>> area_map_fr;
  // エリア情報_スペイン語
  public Map<String, List<String>> area_map_es;
  
  public Map<String, List<String>> getAreaMap(String locale) {
    if ("ja".equals(locale) && area_map_ja != null) {
      return area_map_ja;
    }
    if ("ko".equals(locale) && area_map_ko != null) {
      return area_map_ko;
    }
    if ("zh".equals(locale) && area_map_zh != null) {
      return area_map_zh;
    }
    if ("fr".equals(locale) && area_map_fr != null) {
      return area_map_fr;
    }
    if ("es".equals(locale) && area_map_es != null) {
      return area_map_es;
    }
    return area_map; // 英語
  }
}
