package com.shenhesoft.logistics.system;

import org.hibernate.validator.constraints.NotBlank;

public class SystemManage {
  // @NotBlank(message="手机号不能为空")
  private String phone;
  // @NotBlank(message="验证码不能为空")
  private String checkCode;
  private String passwd;
  private String passwdAgain;

  private String action;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCheckCode() {
    return checkCode;
  }

  public void setCheckCode(String checkCode) {
    this.checkCode = checkCode;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getPasswdAgain() {
    return passwdAgain;
  }

  public void setPasswdAgain(String passwdAgain) {
    this.passwdAgain = passwdAgain;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

}
