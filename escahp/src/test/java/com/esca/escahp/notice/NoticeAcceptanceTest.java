package com.esca.escahp.notice;

import com.esca.escahp.notice.dto.NoticeRequest;
import com.esca.escahp.notice.dto.NoticeResponse;
import com.esca.escahp.notice.entity.Notice;
import com.esca.escahp.notice.repository.NoticeRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.esca.escahp.DataLoader.token;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NoticeAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private NoticeRepository noticeRepository;

    @BeforeEach
    void before() {
        RestAssured.port = port;

        Notice notice1 = new Notice("title", "category", "writer", "content", "file");
        Notice notice2 = new Notice("title2", "category2", "writer2", "content2", "file2");
        Notice notice3 = new Notice("title3", "category3", "writer3", "content3", "file3");
        noticeRepository.save(notice1);
        noticeRepository.save(notice2);
        noticeRepository.save(notice3);
    }

    @AfterEach
    void after() {
        noticeRepository.deleteAll();
    }

    @Test
    void getAllNotices() {
        List<NoticeResponse> responses = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/notices")
                .then()
                .extract()
                .body().jsonPath().getList(".", NoticeResponse.class);

        assertThat(responses.size()).isEqualTo(3);
    }

    @Test
    void getNoticeById() {
        Notice notice = noticeRepository.findAll().get(0);
        NoticeResponse response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/notices/{id}", notice.getId())
                .then()
                .extract()
                .as(NoticeResponse.class);
        assertAll(
                () -> assertEquals(notice.getTitle(), response.getTitle()),
                () -> assertEquals(response.getViewCnt(), notice.getViewCnt() + 1)
        );
    }

    @Test
    void insertNotice() {
        NoticeRequest request = new NoticeRequest("titlee", "category", "writer", "content", "file");
        ExtractableResponse<Response> response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/notices")
                .then()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void updateNotice() {
        Notice notice = noticeRepository.findAll().get(0);
        String updatedTitle = "updatedTitle";
        NoticeRequest request = new NoticeRequest(updatedTitle, notice.getCategory(), notice.getWriter(), notice.getWriter(), notice.getFile());
        NoticeResponse response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .put("/notices/{id}", notice.getId())
                .then()
                .extract()
                .as(NoticeResponse.class);

        assertEquals(response.getTitle(), updatedTitle);
    }

    @Test
    void deleteNotice() {
        Notice notice = noticeRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .delete("/notices/{id}", notice.getId())
                .then()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}