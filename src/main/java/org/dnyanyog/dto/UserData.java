package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class UserData {

  @NotNull(message = "Username is must required")
  @NotBlank(message = "Username should not blank")
  @Size(min = 6, max = 20, message = "Username should be minimum 6 to 20 characters")
  private String username;

  @NotNull(message = "Password is manadatory")
  @NotBlank(message = "Password Should Not blank")
  @Size(min = 6, max = 15, message = "password should be minimum 6 to 15 characters")
  private String password;

  @NotNull(message = "Email is manadatory")
  @NotBlank(message = "Email Should Not blank")
  @Email(message = "Enter valid Email")
  private String email;

  @NotNull(message = "Age is manadatory")
  @NotBlank(message = "Age Should Not blank")
  @Positive(message = "Enter Positive Integer Age")
  private String age;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
