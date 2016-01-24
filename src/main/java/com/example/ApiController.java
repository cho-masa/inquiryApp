package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.CityInfo;
import bean.PrefInfo;

// API用のコントローラクラス
@RestController
public class ApiController {
  
  // 市区町村一覧の取得
  @RequestMapping("api/cities")
  List<CityInfo> getCities(@RequestParam String prefCd) {
    PrefInfo prefInfo = Constant.getInstance().pref_map.get(prefCd);
    List<CityInfo> cityList = prefInfo == null ? new ArrayList<CityInfo>() : prefInfo.getCityList();
    return cityList;
  }
}
