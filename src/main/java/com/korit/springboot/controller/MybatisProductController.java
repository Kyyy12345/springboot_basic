package com.korit.springboot.controller;

import com.korit.springboot.dto.InsertProductReqDto;
import com.korit.springboot.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisProductController {

    @Autowired //인스턴스 하나 생성되고 주입이 된다
    private ProductMapper productMapper;

    @PostMapping("/mybatis/product")
    public ResponseEntity<?> insert(@RequestBody InsertProductReqDto insertProductReqDto) {
        productMapper.insert( insertProductReqDto.getProduct_name(),
                insertProductReqDto.getSize(), insertProductReqDto.getPrice());
        return ResponseEntity.ok().build();
    }
}
