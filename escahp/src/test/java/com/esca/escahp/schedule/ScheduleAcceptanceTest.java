package com.esca.escahp.schedule;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("연간일정 인수 테스트")
public class ScheduleAcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("연간일정들을 가져온다.")
    void getScheduleList() {
    }

    @Test
    @DisplayName("연간일정을 하나 가져온다.")
    void selectSchedule() {
    }

    @Test
    @DisplayName("연간일정을 추가한다.")
    void addSchedule() {
    }

    @Test
    @DisplayName("연간일정을 수정한다.")
    void updateSchedule() {
    }

    @Test
    @DisplayName("연간일정을 삭제한다.")
    void deleteSchedule() {
    }
}
