package com.esca.escahp.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.board.dto.FreeRequest;
import com.esca.escahp.board.dto.FreeResponse;
import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.repository.FreeRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("자유게시판 인수 테스트")
class FreeAcceptanceTest {
    @LocalServerPort
    int port;

    @Autowired
    FreeRepository freeRepository;

    @BeforeEach
    void before() {
        RestAssured.port = port;

        FreeBoard free1 = new FreeBoard("title1", "content1", "writer1", "example1.jpg");
        FreeBoard free2 = new FreeBoard("title2", "content2", "writer2", "example2.jpg");
        FreeBoard free3 = new FreeBoard("title3", "content3", "writer3", "example3.jpg");
        freeRepository.save(free1);
        freeRepository.save(free2);
        freeRepository.save(free3);
    }

    @AfterEach
    void after() {
        freeRepository.deleteAll();
    }

    @Test
    @DisplayName("자우게시판 목록을 가져온다.")
    void getAllFreeBoard() {
        List<FreeResponse> responses = RestAssured.given()
            .when()
            .get("/free")
            .then()
            .extract()
            .body().jsonPath().getList(".", FreeResponse.class);

        assertThat(responses.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("자유게시물을 가져온다.")
    void getFreeBoardById() {
        FreeBoard freeBoard = freeRepository.findAll().get(0);
        FreeResponse response = RestAssured.given()
            .when()
            .get("/free/{id}", freeBoard.getId())
            .then()
            .extract()
            .as(FreeResponse.class);

        assertEquals(freeBoard.getTitle(), response.getTitle());
    }

    @Test
    @DisplayName("존재하지 않는 자유게시물을 가져온다.")
    void getNotFoundFreeBoardById() {
        // 예외처리 리팩토링 후 작성 예정
    }

    @Test
    @DisplayName("삭제된 자유게시물을 가져온다.")
    void getDeleteFreeBoardById() {
        // 예외처리 리팩토링 후 작성 예정
    }

    @Test
    @DisplayName("자유게시물을 추가한다.")
    void postFreeBoard() {
        FreeRequest freeRequest = new FreeRequest("title", "content", "writer", "example.jpg");
        ExtractableResponse<Response> response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(freeRequest)
            .post("/free")
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("자유게시물을 수정한다.")
    void updateFreeBoard() {
        FreeBoard freeBoard = freeRepository.findAll().get(0);
        FreeRequest request = new FreeRequest("updateTitle", "updateContent", freeBoard.getWriter(), "updateImg.jpg");
        ExtractableResponse<Response> response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(request)
            .put("/free/{id}", freeBoard.getId())
            .then()
            .extract();
        FreeResponse update = RestAssured.given()
            .when()
            .get("/free/{id}", freeBoard.getId())
            .then()
            .extract()
            .as(FreeResponse.class);

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
            () -> assertEquals(update.getId(), freeBoard.getId()),
            () -> assertEquals(update.getTitle(), request.getTitle()),
            () -> assertEquals(update.getContent(), request.getContent()),
            () -> assertEquals(update.getWriter(), request.getWriter()),
            () -> assertEquals(update.getFile(), request.getFile())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 자유게시물을 수정한다.")
    void updateFreeBoardByNotWriter() {
        // 유저로 리팩토링 후 작성 예정
    }

    @Test
    @DisplayName("자유게시물을 삭제한다.")
    void deleteFreeBoard() {
        FreeBoard freeBoard = freeRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .patch("/free/{id}", freeBoard.getId())
            .then()
            .extract();
        FreeBoard delete = freeRepository.getById(freeBoard.getId());

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
            () -> assertEquals(delete.getDeleteYn(), "Y"),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 자유게시물을 삭제한다.")
    void deleteFreeBoardByNotWriter() {
        // 유저로 리팩토링 후 작성 예정
    }
}