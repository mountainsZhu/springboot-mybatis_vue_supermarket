package com.zss.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

  private String username;
  private String password;
  private int power;
  private String nickname;
  private String phone;
  private String sex;
  private String email;
  private String testcode;
}
