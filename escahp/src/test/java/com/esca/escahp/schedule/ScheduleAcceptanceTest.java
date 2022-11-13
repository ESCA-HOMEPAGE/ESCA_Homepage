package com.esca.escahp.schedule;

import com.esca.escahp.schedule.dto.ScheduleRequest;
import com.esca.escahp.schedule.dto.ScheduleResponse;
import com.esca.escahp.schedule.entity.Schedule;
import com.esca.escahp.schedule.repository.ScheduleRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static com.esca.escahp.DataLoader.token;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("연간일정 인수 테스트")
public class ScheduleAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    void before() {
        RestAssured.port = port;

        Schedule schedule1 = new Schedule("scheduleTitle1", "tag", "scheduleContent1", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Schedule schedule2 = new Schedule("scheduleTitle2", "tag", "scheduleContent2", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Schedule schedule3 = new Schedule("scheduleTitle3", "tag", "scheduleContent3", LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        scheduleRepository.save(schedule3);
    }

    @AfterEach
    void after() {
        scheduleRepository.deleteAll();
    }

    @Test
    @DisplayName("연간일정들을 가져온다.")
    void getScheduleList() {
        List<ScheduleResponse> responses = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/schedules")
                .then()
                .extract()
                .body().jsonPath().getList(".", ScheduleResponse.class);

        assertThat(responses.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("연간일정을 하나 가져온다.")
    void selectSchedule() {
        Schedule schedule = scheduleRepository.findAll().get(0);
        ScheduleResponse response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/schedules/{id}", schedule.getId())
                .then()
                .extract()
                .as(ScheduleResponse.class);

        assertEquals(schedule.getTitle(), response.getTitle());
    }

    @Test
    @DisplayName("연간일정을 추가한다.")
    void addSchedule() {
        ScheduleRequest request = new ScheduleRequest("title", "tag", "content", LocalDateTime.now(), LocalDateTime.now());
        ExtractableResponse<Response> response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/schedules")
                .then()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("연간일정을 수정한다.")
    void updateSchedule() {
        Schedule schedule = scheduleRepository.findAll().get(0);
        String updatedTitle = "updatedTitle";
        String updatedTag = "otherTag";
        String updatedContent = "updatedContent";
        ScheduleRequest request = new ScheduleRequest(updatedTitle, updatedTag, updatedContent, LocalDateTime.now(), LocalDateTime.now());

        ScheduleResponse response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .put("/schedules/{id}", schedule.getId())
                .then()
                .extract()
                .as(ScheduleResponse.class);

        assertAll(
                () -> Assertions.assertEquals(schedule.getId(), response.getId()),
                () -> Assertions.assertEquals(response.getTitle(), updatedTitle),
                () -> Assertions.assertEquals(response.getTag(), updatedTag),
                () -> Assertions.assertEquals(response.getContent(), updatedContent)
        );
    }

    @Test
    @DisplayName("연간일정을 삭제한다.")
    void deleteSchedule() {
        Schedule schedule = scheduleRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .delete("/schedules/{id}", schedule.getId())
                .then()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
