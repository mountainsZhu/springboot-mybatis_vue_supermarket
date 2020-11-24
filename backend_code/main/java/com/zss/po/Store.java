package com.zss.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

  private String storeid;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.util.Date storetime;
  private String storename;
  private int isup;
  private int amount;
  private int badamount;
  private int upperlimit;
  private int lowerlimit;

  public Store(java.util.Date storetime,String storename,int amount){
      this.amount =amount;
      this.storename = storename;
      this.storetime = storetime;
  }

}
