package com.zss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleMsg implements Serializable {
    private String salename;
    private int saleamount;
    private float saleprice;
    private float sumsale;
}
