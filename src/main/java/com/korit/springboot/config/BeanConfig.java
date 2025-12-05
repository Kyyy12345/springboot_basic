package com.korit.springboot.config;

import com.mysql.cj.CharsetMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeanConfig {

    /*@Component
    public class A {
        public A() {
            System.out.println("a 호출");
        }
        public void print() {
            System.out.println("print 호출");
        }

    }*/

    // 설정 컴포넌트들 생성 여기다 등록해서 쓴다
    /*  @Bean
    public CharsetMapping bb() {
        System.out.println("a 호출");
        return new CharsetMapping();
    }*/
}
