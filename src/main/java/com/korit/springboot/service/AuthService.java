package com.korit.springboot.service;

import com.korit.springboot.dto.CreateUserReqDto;
import com.korit.springboot.dto.SigninReqDto;
import com.korit.springboot.dto.SignupReqDto;
import com.korit.springboot.entity.UserEntity;
import com.korit.springboot.exception.DuplicatedException;
import com.korit.springboot.jwt.JwtTokenProvider;
import com.korit.springboot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.dsig.SignedInfo;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(rollbackFor = Exception.class)
    public int signup(SignupReqDto dto) {
        UserEntity userEntity = dto.toEntity(passwordEncoder);
        userMapper.insert(userEntity);
        return userEntity.getUserId();
    }

    public void duplicatedUsername(String username) {
        UserEntity foundUser = userMapper.findUserByUsername(username);

        if(foundUser != null) {
            throw new DuplicatedException("username", "중복됨");
        }
    }

    public String signin(SigninReqDto dto) {
        final String username = dto.getUsername();
        final String password = dto.getPassword();
        final String defaultMessage = "사용자 정보를 확인하세요.";

        UserEntity foundUser = userMapper.findUserByUsername(username);
        if(foundUser == null) {
            throw new UsernameNotFoundException(defaultMessage);
        }
        if(!passwordEncoder.matches(password, foundUser.getPassword())){
            throw new BadCredentialsException(defaultMessage);
        }

        //토큰 생성
        final String accessToken = jwtTokenProvider.createAuthenticationToken(foundUser);

        return accessToken;
    }

}
