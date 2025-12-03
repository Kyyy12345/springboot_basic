package com.korit.springboot.controller;

import com.korit.springboot.dto.ReqDataDto6;
import com.korit.springboot.dto.ReqFormDataDto4;
import com.korit.springboot.dto.ReqJsonDto2;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class RequestDataController2 {

    // Post 요청 데이터 받는 방법 1 - Map을 통해 JSON 데이터 받는 방법
    @PostMapping("/req/data1")
    public ResponseEntity<?> reqPost1(@RequestBody Map<String, Object> body) {
        System.out.println(body);
        //System.out.println(body.get("additionalProp2"));
        return ResponseEntity.ok().build();
    }

    // Post 요청 데이터 받는 방법 2 - DTO를 통해 JSON 데이터 받는 방법
    @PostMapping("/req/data2")
    public ResponseEntity<?> reqPost2(@RequestBody ReqJsonDto2 data) {
        System.out.println(data);
        System.out.println(data.getAge());
        return ResponseEntity.ok().build();
    }

    // Post 요청 데이터 받는 방법 3 - 파일 데이터 받는법
    // 파일 전송을 위해서는 요청 Content-Type이 꼭 multipart/form-DATA
    @PostMapping(value = "/req/data3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> reqPost3(@RequestPart MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return ResponseEntity.ok().build();
    }

    // Post 요청 데이터 받는 방법 4 - 파일 데이터 받는법
    // 파일 전송을 위해서는 요청 Content-Type이 꼭 multipart/form-DATA
    @PostMapping(value = "/req/data4", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> reqPost4(ReqFormDataDto4 dto) {
        System.out.println(dto);
        System.out.println(dto.getFile().getOriginalFilename());
        return ResponseEntity.ok().build();
    }

    // Post 요청 데이터 받는 방법 5 - 여러 파일 데이터 받는법
    // 파일 전송을 위해서는 요청 Content-Type이 꼭 multipart/form-DATA
    @PostMapping(value = "/req/data5", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> reqPost5(@RequestPart("file") List<MultipartFile> files) {
        files.forEach(file -> System.out.println(file.getOriginalFilename()));
        return ResponseEntity.ok().build();
    }

    // Put 요청 데이터 받는 방법 1 - JSON 데이터만 보통 사용 / 전체수정
    @PutMapping("/req/data1/{id}")
    public ResponseEntity<?> reqPut(@PathVariable int id,@RequestBody ReqJsonDto2 dto) {
        System.out.println(dto);
        return ResponseEntity.ok().build();
    }

    // Patch 요청 데이터 받는 방법 1 - JSON 데이터만 보통 사용 / 부분 수정
    @PatchMapping("/req/data1/{id}")
    public ResponseEntity<?> reqPatch(@PathVariable int id,@RequestBody ReqJsonDto2 dto) {
        System.out.println(dto);
        return ResponseEntity.ok().build();
    }

    // delete는 id만 쓸꺼다
    @DeleteMapping("/req/data1/{id}")
    public ResponseEntity<?> reqDelete(@PathVariable int id, @RequestBody Map<String, Object> data) {
        System.out.println(id);
        System.out.println(data);
        return ResponseEntity.ok().build();
    }
}
