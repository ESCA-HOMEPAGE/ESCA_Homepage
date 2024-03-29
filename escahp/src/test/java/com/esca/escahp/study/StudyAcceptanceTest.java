package com.esca.escahp.study;

import com.esca.escahp.study.dto.StudyResponse;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.repository.StudyRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.esca.escahp.DataLoader.token;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("스터디 게시판 인수 테스트")
public class StudyAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private StudyRepository studyRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        StudyBoard board1 = new StudyBoard("title", "content", "writer", "category", "file");
        studyRepository.save(board1);
    }

    @AfterEach
    void tearDown() {
        studyRepository.deleteAll();
    }

    @Test
    @DisplayName("스터디 게시물들을 가져온다.")
    public void getStudyBoardList() {
        List<StudyResponse> responses = RestAssured.given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/study")
                .then()
                .extract()
                .body().jsonPath().getList(".", StudyResponse.class);

        assertAll(
                () -> assertThat(responses.size()).isEqualTo(1),
                () -> assertThat(responses.get(0).getTitle()).isEqualTo("title"),
                () -> assertThat(responses.get(0).getContent()).isEqualTo("content")
        );
    }
}
