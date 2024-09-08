package org.example.newdiary;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class NewDiaryApplicationTests {

    @BeforeEach
    void before() {
        log.info("모든 테스트 전 실행");
    }

    @AfterEach
    void after(){
        log.info("모든 테스트 후 실행");
    }
    @Test
    void testTesting(){
        log.info("테스트 테스트");
    }
}
