package org.dnyanyog.enums;

public enum ResponseCode {
  SUCCESS_USER_ADD("Success", "User added successfuly"),
  SUCCESS_USER_GET("Success", "User found"),

  SUCCESS_USER_LOGIN("Success", "Login successful"),
  FAILED_USER_LOGIN("Fail", "Login failed");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
