package bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class InquiryForm {
  // 名前
  @NotNull
  @NotBlank
  @Size(max = 127)
  private String name;
  // eメール
  @NotNull
  @NotBlank
  @Email
  private String email;
  // 問い合わせ項目
  @NotNull
  @NotBlank
  private String type;
  // 問い合わせ内容
  @NotNull
  @NotBlank
  @Size(max = 30)
  private String content;
  
  @NotBlank
  private String pref;
  @NotBlank
  private String city;
}
