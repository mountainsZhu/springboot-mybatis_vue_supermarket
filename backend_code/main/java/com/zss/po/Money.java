package com.zss.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Money implements Serializable {

  private String moneyid;
  private double supermarketmoney;
  private double customermoney;

}
