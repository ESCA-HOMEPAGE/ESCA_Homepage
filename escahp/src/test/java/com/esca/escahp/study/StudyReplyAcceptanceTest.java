package com.esca.escahp.study;

import static com.esca.escahp.DataLoader.token;
import static com.esca.escahp.DataLoader.user1;
import static com.esca.escahp.DataLoader.user2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.study.dto.StudyReplyRequest;
import com.esca.escahp.study.dto.StudyReplyResponse;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.entity.StudyReply;
import com.esca.escahp.study.repository.StudyReplyRepository;
import com.esca.escahp.study.repository.StudyRepository;
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
@DisplayName("스터디게시판 댓글 인수 테스트")
public class StudyReplyAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyReplyRepository studyReplyRepository;

    @BeforeEach
    void before() {
        RestAssured.port = port;

        StudyBoard studyBoard = new StudyBoard("title1", "content1", "nickOne", "2022 여름", "example1.jpg");
        StudyBoard deletedBoard = new StudyBoard("title2", "content2", "nickTwo", "2022 여름", "example2.jpg");
        StudyReply studyReply1 = new StudyReply(studyBoard, user1, "comment1");
        StudyReply studyReply2 = new StudyReply(studyBoard, user1, "comment2");
        StudyReply studyReply3 = new StudyReply(studyBoard, user1, "comment3");
        deletedBoard.delete();
        studyReply2.delete();
        studyRepository.save(studyBoard);
        studyRepository.save(deletedBoard);
        studyReplyRepository.save(studyReply1);
        studyReplyRepository.save(studyReply2);
        studyReplyRepository.save(studyReply3);
    }

    @AfterEach
    void after() {
        studyReplyRepository.deleteAll();
        studyRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판의 댓글 목록을 가져온다.")
    void getStudyReplyListFromStudyBoard() {
        StudyBoard studyBoard = studyRepository.findAll().get(0);
        List<StudyReplyResponse> list = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/study/{postId}/reply", studyBoard.getId())
            .then()
            .log().all()
            .extract()
            .body().jsonPath().getList(".", StudyReplyResponse.class);

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getStudyReplyListFromDeletedBoard() {
        StudyBoard studyBoard = studyRepository.findAll().get(1);
        System.out.println("studyBoard deleteYn : " + studyBoard.getDeleteYn());

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/study/{postId}/reply", studyBoard.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postStudyReply() {
        StudyReplyRequest request = new StudyReplyRequest("new comment");
        StudyBoard studyBoard = studyRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/study/{postId}/reply/{userId}", studyBoard.getId(), user1.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가한다.")
    void postStudyReplyToDeletedBoard() {
        StudyReplyRequest request = new StudyReplyRequest("new comment");
        StudyBoard studyBoard = studyRepository.findAll().get(1);
        System.out.println("studyBoard deleteYn : " + studyBoard.getDeleteYn());

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/study/{postId}/reply/{userId}", studyBoard.getId(), user1.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteReply() {
        StudyReply studyReply = studyReplyRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .patch("/study/reply/{id}/{userId}", studyReply.getId(), studyReply.getUser().getId())
            .then()
            .log().all()
            .extract();
        StudyReply delete = studyReplyRepository.getById(studyReply.getId());

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value()),
            () -> assertThat(delete.getDeleteYn()).isEqualTo("Y"),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제한다.")
    void deleteReplyByNotWriter() {
        StudyReply studyReply = studyReplyRepository.findAll().get(0);
        User user = studyReply.getUser().equals(user1) ? user2 : user1;

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .patch("/study/reply/{id}/{userId}", studyReply.getId(), user.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.FORBIDDEN_DELETE_REPLY.getStatus());
    }
}
