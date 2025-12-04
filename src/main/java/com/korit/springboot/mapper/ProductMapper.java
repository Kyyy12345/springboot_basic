package com.korit.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {
    int insert(
              @Param("size") String a,
              @Param("product_name") String b,
              @Param("price") int c
    );
}
