package com.esca.escahp.board;

import static com.esca.escahp.DataLoader.token;
import static com.esca.escahp.DataLoader.user1;
import static com.esca.escahp.DataLoader.user2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.board.dto.FreeReplyRequest;
import com.esca.escahp.board.dto.FreeReplyResponse;
import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.entity.FreeReply;
import com.esca.escahp.board.repository.FreeReplyRepository;
import com.esca.escahp.board.repository.FreeRepository;
import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.user.entity.User;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("자유게시판 댓글 인수 테스트")
class FreeReplyAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private FreeRepository freeRepository;
    @Autowired
    private FreeReplyRepository freeReplyRepository;

    @BeforeEach
    void before() {
        RestAssured.port = port;

        FreeBoard freeBoard = new FreeBoard("title1", "content1", "nickOne", "example1.jpg");
        FreeBoard deletedBoard = new FreeBoard("title2", "content2", "nickTwo", "example2.jpg");
        FreeBoard blindedBoard = new FreeBoard("blind title", "blind content",
            "nickOne", "example.jpg", 5);
        FreeReply freeReply1 = new FreeReply(freeBoard, user1, "comment1");
        FreeReply freeReply2 = new FreeReply(freeBoard, user2, "comment2");
        FreeReply freeReply3 = new FreeReply(freeBoard, user1, "comment3");
        FreeReply freeReply4 = new FreeReply(deletedBoard, user1, "comment4");
        FreeReply freeReply5 = new FreeReply(blindedBoard, user1, "comment5");
        deletedBoard.delete();
        freeRepository.save(freeBoard);
        freeRepository.save(deletedBoard);
        freeRepository.save(blindedBoard);
        freeReplyRepository.save(freeReply1);
        freeReplyRepository.save(freeReply2);
        freeReplyRepository.save(freeReply3);
        freeReplyRepository.save(freeReply4);
        freeReplyRepository.save(freeReply5);
    }

    @AfterEach
    void after() {
        freeReplyRepository.deleteAll();
        freeRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판의 댓글 목록을 가져온다.")
    void getAllFreeReplyByPostId() {
        FreeBoard freeBoard = freeRepository.findAll().get(0);
        List<FreeReplyResponse> list = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/free/{postId}/reply", freeBoard.getId())
            .then()
            .extract()
            .body().jsonPath().getList(".", FreeReplyResponse.class);

        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getFreeReplyListFromDeleteBoard() {
        FreeBoard freeBoard = freeRepository.findAll().get(1);
        System.out.println("freeBoard deleteYn : " + freeBoard.getDeleteYn());

        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/free/{postId}/reply", freeBoard.getId())
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("블라인드된 게시판의 댓글 목록을 가져온다.")
    void getFreeReplyListFromBlindedBoard() {
        FreeBoard freeBoard = freeRepository.findAll().get(2);
        System.out.println("freeBoard report : " + freeBoard.getReport());

        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/free/{postId}/reply", freeBoard.getId())
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postFreeReply() {
        FreeReplyRequest request = new FreeReplyRequest("new comment");
        FreeBoard freeBoard = freeRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/free/{postId}/reply/{userId}", freeBoard.getId(), user2.getId())
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가하려한다.")
    void postFreeReplyFromDeleteBoard() {
        FreeReplyRequest request = new FreeReplyRequest("new comment");
        FreeBoard freeBoard = freeRepository.findAll().get(1);
        System.out.println("freeBoard deleteYn : " + freeBoard.getDeleteYn());

        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/free/{postId}/reply/{userId}", freeBoard.getId(), user2.getId())
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("블라인드된 게시판에 댓글을 추가하려한다.")
    void postFreeReplyFromBlindedBoard() {
        FreeReplyRequest request = new FreeReplyRequest("new comment");
        FreeBoard freeBoard = freeRepository.findAll().get(2);
        System.out.println("freeBoard report : " + freeBoard.getReport());

        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/free/{postId}/reply/{userId}", freeBoard.getId(), user2.getId())
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteReply() {
        FreeReply freeReply = freeReplyRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .patch("/free/reply/{id}/{userId}", freeReply.getId(), freeReply.getUser().getId())
            .then()
            .extract();
        FreeReply delete = freeReplyRepository.getById(freeReply.getId());

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value()),
            () -> assertEquals("Y", delete.getDeleteYn()),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제하려한다.")
    void deleteFreeReplyFromNotWriter() {
        FreeReply freeReply = freeReplyRepository.findAll().get(0);
        User user;
        if (freeReply.getUser().equals(user1)) {
            user = user2;
        } else {
            user = user1;
        }

        ExtractableResponse<Response> response = RestAssured.given()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .patch("/free/reply/{id}/{userId}", freeReply.getId(), user.getId())
            .then()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.FORBIDDEN_DELETE_REPLY.getStatus());
    }
}