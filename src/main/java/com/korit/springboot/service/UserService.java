package com.korit.springboot.service;

import com.korit.springboot.dto.CreateUserReqDto;
import com.korit.springboot.entity.UserEntity;
import com.korit.springboot.exception.DuplicatedException;
import com.korit.springboot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserMapper userMapper;

    //Transactional
    //커밋을 안함 - 무효화
    //insert, update, delete
    //예외 터지면 롤백하란 의미
    /*@Transactional(rollbackFor = Exception.class)
    public int createUser(CreateUserReqDto dto) {
        //UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(dto.getUsername());  - 귀찮아서 안씀
        UserEntity userEntity = dto.toEntity();
        System.out.println(userEntity);
        userMapper.insert(userEntity);
        System.out.println(userEntity);
        return userEntity.getUserId();

    }*/

    @Transactional(rollbackFor = Exception.class)
    public int createUser(CreateUserReqDto dto) {
        UserEntity userEntity = dto.toEntity();
        userMapper.insert(userEntity);
        return userEntity.getUserId();
    }

    public void duplicatedUsername(String username) {
        UserEntity foundUser = userMapper.findUserByUsername(username);
        if (foundUser != null) {
            throw new DuplicatedException("username", "이미 존재함");
        }
    }

}