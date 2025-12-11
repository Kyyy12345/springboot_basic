package com.korit.springboot.filter;

import com.korit.springboot.entity.UserEntity;
import com.korit.springboot.jwt.JwtTokenProvider;
import com.korit.springboot.mapper.UserMapper;
import com.korit.springboot.security.PrincipalUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

// 회원가입, 로그인, 토큰발행해야 이밑에 작업할 수 있다
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //OncePerRequestFilter - 인증은 한번만 일어나면 됨 - 한 요청당 jwt 한번만 확인하겠다

    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 팔찌를 안 채워주고 넘기는 행위
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = bearerToken.replaceAll("Bearer ", "");

        if(!jwtTokenProvider.validateToken(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        int userId = jwtTokenProvider.getUserId(accessToken);
        UserEntity foundUser = userMapper.findUserByUserId(userId);

        if (foundUser == null) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(foundUser);

        PrincipalUser principalUser = new PrincipalUser(foundUser);
        String password = "";
        Collection<? extends GrantedAuthority> authorities = principalUser.getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principalUser, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication); // 팔찌 채워주기
        filterChain.doFilter(request, response);
    }
    //삐졌어~??? 미안해~~ 진짜 다 적고 밑에 고민중이어씀!!!!
}
