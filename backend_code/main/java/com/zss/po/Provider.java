package com.zss.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider implements Serializable {

  private String providername;
  private String phone;
  private String address;
  private String email;

}
