package com.korit.springboot.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    int productInsert(@Param("product_name") String product_name, @Param("size") String size, @Param("price") int price );
    @MapKey("product_id")
    List<Map<String, Object>> findAllProduct();
}
