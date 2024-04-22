package org.dnyanyog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class LoginRequest {

  @NotNull(message = "Username is must required")
  @NotBlank(message = "Username should not blank")
  @Size(min = 6, max = 20, message = "Username should be minimum 6 to 20 characters")
  private String username;

  @NotNull(message = "Password is manadatory")
  @NotBlank(message = "Password Should Not blank")
  @Size(min = 6, max = 15, message = "password should be minimum 6 to 15 characters")
  private String password;

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
}
