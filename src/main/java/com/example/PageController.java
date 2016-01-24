package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bean.CityInfo;
import bean.PrefInfo;
import bean.InquiryForm;

import com.example.mail.InquiryMailBuilder;
import com.example.mail.VelocityUtils;

// 画面用のコントローラクラス
@Controller
public class PageController {

  @ModelAttribute
  InquiryForm setUpForm() {
    return new InquiryForm();
  }
  
  @Autowired
  private VelocityUtils velocityUtils;
  
  @Autowired
  private EmailService emailService;
  
  // 入力画面：表示
  @RequestMapping(value="inquiry/")
  String input(Model model) {
    return "inquiry/input";
  }
  
  // 入力画面：確認ボタン押下
  @RequestMapping(value="inquiry/", params="confirm", method = RequestMethod.POST)
  String confirm(@Validated InquiryForm form, BindingResult result, Model model) {
    // エラーチェック
    if (result.hasErrors()) {
      return input(model);
    }
    return "inquiry/confirm";
  }
  
  // 確認画面：送信ボタン押下
  @RequestMapping(value="inquiry/", params="send", method = RequestMethod.POST)
  String send(@Validated InquiryForm form, BindingResult result, Model model) {
    // エラーチェック
    if (result.hasErrors()) {
      return input(model);
     }

    // メール送信
    SimpleMailMessage mailMessage = InquiryMailBuilder.build()
        .setForm(form)
        .setVelocityUtils(velocityUtils)
        .setTemplateLocation("inquiry/mail-body.vm")
        .create();
    emailService.sendSimpleMail(mailMessage);;

    // 完了画面へ遷移
    return "redirect:/inquiry/comp";
  }
  
  // 確認画面：戻るボタン押下
  @RequestMapping(value="inquiry/", params="goToInput")
  String goToInput(@Validated InquiryForm form, BindingResult result, Model model) {
    return "inquiry/input";
  }
  
  // 完了画面：表示
  @RequestMapping(value="inquiry/comp")
  String comp() {
    return "inquiry/comp";
  }
  
  // ajaxデモ画面：表示
  @RequestMapping(value="inquiry/ajax-demo")
  String ajaxDemo(Model model) {
    String prefCd = "cd2"; // test-cd
    String cityCd = "2"; // test-cd

    PrefInfo prefInfo = Constant.getInstance().pref_map.get(prefCd);
    model.addAttribute("prefCd", prefCd);
    model.addAttribute("cityCd", cityCd);
    List<CityInfo> cityList = prefInfo == null ? new ArrayList<CityInfo>() : prefInfo.getCityList();
    model.addAttribute("cities", cityList);

    return "inquiry/ajax-demo";
  }
}
