package bean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.example.validator.Confirm;

import lombok.Data;

@Data
@Confirm(field = "email")
public class InquiryForm {
  // 名前
  @NotBlank
  @Size(max = 127)
  private String name;
  // eメール
  @NotBlank
  @Pattern(regexp = "[0-9()-¥.]*")
  private String phonenumber;
  // eメール
  @NotBlank
  @Email
  private String email;
  // eメール
  @NotBlank
  @Email
  private String confirmEmail;
  // 問い合わせ項目
  @NotBlank
  private String type;
  // 問い合わせ内容
  @NotBlank
  @Size(max = 30)
  private String content;
  
  @NotBlank
  private String pref;
  @NotBlank
  private String city;
}
