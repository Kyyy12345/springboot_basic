package com.korit.springboot.dto;

import lombok.Data;

@Data
public class InsertProductReqDto {
    //private int product_id;
    private String product_name;
    private String size;
    private int price;
}
