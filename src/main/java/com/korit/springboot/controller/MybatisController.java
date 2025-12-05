package com.korit.springboot.controller;

import com.korit.springboot.dto.InsertProductReqDto;
import com.korit.springboot.dto.InsertStudyRequestDto;
import com.korit.springboot.dto.ReqJsonDto2;
import com.korit.springboot.mapper.ProductMapper;
import com.korit.springboot.mapper.StudyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MybatisController {

    @Autowired //인스턴스 하나 생성되고 주입이 된다
    private StudyMapper studyMapper;
    @Autowired
    private ProductMapper productMapper;

    @PostMapping("/mybatis/study")
    public ResponseEntity<?> insert(@RequestBody InsertStudyRequestDto stDto) {

        studyMapper.insert(stDto.getName(), stDto.getAge());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mybatis/product")
    public ResponseEntity<?> productInsert(@RequestBody InsertProductReqDto pdDto) {

        productMapper.productInsert(pdDto.getProduct_name(), pdDto.getSize(), pdDto.getPrice());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mybatis/students")
    public ResponseEntity<?> selectAllStudents() {
        List<String> studentList = studyMapper.findAllName();
        return ResponseEntity.ok(studentList);
    }


    /*@GetMapping("/mybatis/products")
    public ResponseEntity<?> selectAllProducts() {
        List<Map<String, Object>> productsList = productMapper.();
        return ResponseEntity.ok(productsList);
    }*/
}
