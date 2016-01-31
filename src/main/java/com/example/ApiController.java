package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// API用のコントローラクラス
@RestController
public class ApiController {
  
  // 市区町村一覧の取得
  @RequestMapping("api/cities")
  List<String> getCities(@RequestParam String prefCd, Locale locale) {

    List<String> cities = 
    StringUtils.isEmpty(prefCd)? new ArrayList<String>() : Constant.getInstance().getAreaMap(locale.getLanguage()).get(prefCd);
    return cities;
  }
}
