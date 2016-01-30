package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
  @RequestMapping(value="inquiry")
  String input(Model model, InquiryForm form) {

    String prefCd = form.getPref();
    if (StringUtils.isEmpty(prefCd)) {
      prefCd = "cd1";
    }
    PrefInfo prefInfo = Constant.getInstance().pref_map.get(prefCd);
    List<CityInfo> cityList = prefInfo == null ? new ArrayList<CityInfo>() : prefInfo.getCityList();
    model.addAttribute("cities", cityList);
    return "inquiry/input";
  }
  
  // 入力画面：確認ボタン押下
  @RequestMapping(value="inquiry", params="confirm", method = RequestMethod.POST)
  String confirm(@Validated InquiryForm form, BindingResult result, Model model) {
    // エラーチェック
    if (result.hasErrors()) {
      return input(model, form);
    }
    return "inquiry/confirm";
  }
  
  // 確認画面：送信ボタン押下
  @RequestMapping(value="inquiry", params="send", method = RequestMethod.POST)
  String send(@Validated InquiryForm form, BindingResult result, Model model) {
    // エラーチェック
    if (result.hasErrors()) {
      return input(model, form);
     }

    // メール送信
    SimpleMailMessage mailMessage = InquiryMailBuilder.build()
        .setForm(form)
        .setVelocityUtils(velocityUtils)
        .setTemplateLocation("inquiry/mail-body.vm")
        .create();
    emailService.sendSimpleMail(mailMessage);;

    // 完了画面へ遷移
    return "redirect:/inquiry?comp";
  }
  
  // 確認画面：戻るボタン押下
  @RequestMapping(value="inquiry", params="goToInput")
  String goToInput(@Validated InquiryForm form, BindingResult result, Model model) {
    // 確認画面へ遷移
    return input(model, form);
  }
  
  // 完了画面：表示
  @RequestMapping(value="inquiry", params="comp")
  String comp() {
    return "inquiry/comp";
  }
}
