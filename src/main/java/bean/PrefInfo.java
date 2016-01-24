package bean;

import java.util.List;

import lombok.Data;

// 都道府県情報
@Data
public class PrefInfo {
  // 都道府県名
  private String name;
  // 市区町村リスト
  private List<CityInfo> cityList;
  
  
}