package bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class InquiryForm {
  // 名前
  @NotNull
  @Size(min = 1, max = 127)
  private String name;
  // eメール
  @NotNull
  @Email
  private String email;
  // 問い合わせ項目
  @NotNull
  private String type;
  // 問い合わせ内容
  @NotNull
  @Size(min = 1, max = 30)
  private String content;
}
