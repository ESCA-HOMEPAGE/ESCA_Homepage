package com.esca.escahp.gallery;

import static com.esca.escahp.DataLoader.token;
import static com.esca.escahp.DataLoader.user1;
import static com.esca.escahp.DataLoader.user2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.gallery.dto.GalleryReplyRequest;
import com.esca.escahp.gallery.dto.GalleryReplyResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryReply;
import com.esca.escahp.gallery.repository.GalleryReplyRepository;
import com.esca.escahp.gallery.repository.GalleryRepository;
import com.esca.escahp.user.entity.User;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpHeaders;
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
@DisplayName("갤러리 게시판 댓글 인수 테스트")
class GalleryReplyAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private GalleryReplyRepository galleryReplyRepository;

    @BeforeEach
    void before() {
        RestAssured.port = port;

        GalleryBoard galleryBoard = new GalleryBoard("title1", "content1", "nickOne", "category", "example1.jpg");
        GalleryBoard deletedBoard = new GalleryBoard("title2", "content2", "nickTwo", "category", "example2.jpg");
        GalleryReply galleryReply1 = new GalleryReply(galleryBoard, user1, "comment1");
        GalleryReply galleryReply2 = new GalleryReply(galleryBoard, user1, "comment2");
        GalleryReply galleryReply3 = new GalleryReply(galleryBoard, user2, "comment3");
        GalleryReply galleryReply4 = new GalleryReply(deletedBoard, user1, "comment4");
        deletedBoard.delete();
        galleryReply2.delete();
        galleryRepository.save(galleryBoard);
        galleryRepository.save(deletedBoard);
        galleryReplyRepository.save(galleryReply1);
        galleryReplyRepository.save(galleryReply2);
        galleryReplyRepository.save(galleryReply3);
        galleryReplyRepository.save(galleryReply4);
    }

    @AfterEach
    void after() {
        galleryReplyRepository.deleteAll();
        galleryRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판의 댓글 목록을 가져온다.")
    void getAllGalleryReplyByPostId() {
        GalleryBoard board = galleryRepository.findAll().get(0);

        List<GalleryReplyResponse> list = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/gallery/{postId}/reply", board.getId())
            .then()
            .log().all()
            .extract()
            .body().jsonPath().getList(".", GalleryReplyResponse.class);

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getAllGalleryReplyByDeletedPostId() {
        GalleryBoard board = galleryRepository.findAll().get(1);
        System.out.println("galleryBoard deleteYn : " + board.getDeleteYn());

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/gallery/{postId}/reply", board.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postGalleryReply() {
        GalleryReplyRequest request = new GalleryReplyRequest("new comment");
        GalleryBoard board = galleryRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/gallery/{postId}/reply/{userId}", board.getId(), user1.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가한다.")
    void postGalleryReplyToDeletedBoard() {
        GalleryReplyRequest request = new GalleryReplyRequest("new comment");
        GalleryBoard board = galleryRepository.findAll().get(1);
        System.out.println("galleryBoard deleteYn : " + board.getDeleteYn());

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/gallery/{postId}/reply/{userId}", board.getId(), user1.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getStatus());
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteReply() {
        GalleryReply reply = galleryReplyRepository.findAll().get(0);
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .patch("/gallery/reply/{id}/{userId}", reply.getId(), reply.getUser().getId())
            .then()
            .log().all()
            .extract();
        GalleryReply delete = galleryReplyRepository.getById(reply.getId());

        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value()),
            () -> assertThat(delete.getDeleteYn()).isEqualTo("Y"),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제한다.")
    void deleteReplyByNotWriter() {
        GalleryReply reply = galleryReplyRepository.findAll().get(0);
        User user = reply.getUser().equals(user1) ? user2 : user1;
        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .when()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .patch("/gallery/reply/{id}/{userId}", reply.getId(), user.getId())
            .then()
            .log().all()
            .extract();

        assertThat(response.statusCode()).isEqualTo(BoardExceptions.FORBIDDEN_DELETE_REPLY.getStatus());
    }
}