package com.korit.springboot.mapper;

import com.korit.springboot.entity.UserEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(UserEntity userEntity);
    UserEntity findUserByUsername(@Param("username") String username); //중복체크와 user객체 찾기
}