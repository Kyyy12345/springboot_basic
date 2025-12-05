package com.korit.springboot.mapper;

import com.korit.springboot.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        UserEntity userEntity = UserEntity.builder()
                .username("test")
                .password("1234")
                .name("김준일")
                .email("test@gmail.com")
                .build();
        int successCount = userMapper.insert(userEntity);
        System.out.println(successCount);
        Assertions.assertThat(successCount).isEqualTo(1);
    }

    @Test
    void findUserByUsernameTest() {
        UserEntity foundUser = userMapper.findUserByUsername("test1234");
        System.out.println(foundUser);
    }


    //UserController 우리 원래 있던 걸 지우고 저걸로 갈아끼운거거든
    //어제 한건 삭제안하고 UserController 1로 바꿔놨고
    //오늘 거는 UserController2 로 해둠!

    //ㅋㅋㅋㅋㅋ안대까지끼고 꿀잠 자넹
    //내 자리에 말없이 두고 갈 산타는 한 명뿐인데
    //혹시 너야??? 맞으면 감동이구..아니면 좀 민망해서
    //오늘 너 못볼 듯ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
}
