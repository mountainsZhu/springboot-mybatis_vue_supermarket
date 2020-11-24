package com.zss.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zss.SaleMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singlesale implements Serializable {

  private String saleid;
  private String salename;
  private int saleamount;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.util.Date saletime;
  private float saleprice;
  private float sumprice;

  private Product product;
}
