package com.esca.escahp.gallery;

import static com.esca.escahp.DataLoader.user1;
import static com.esca.escahp.DataLoader.user2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.gallery.dto.GalleryReplyRequest;
import com.esca.escahp.gallery.dto.GalleryReplyResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryReply;
import com.esca.escahp.gallery.repository.GalleryReplyRepository;
import com.esca.escahp.gallery.repository.GalleryRepository;
import com.esca.escahp.user.entity.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@ActiveProfiles("test")
class GalleryReplyServiceTest {

    @Autowired
    private GalleryReplyService galleryReplyService;
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private GalleryReplyRepository galleryReplyRepository;

    @BeforeEach
    void before() {
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
    void getGalleryReplyList() {
        GalleryBoard board = galleryRepository.findAll().get(0);
        List<GalleryReplyResponse> list = galleryReplyService.getGalleryReplyList(board.getId());

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getGalleryReplyListFromDeletedBoard() {
        GalleryBoard board = galleryRepository.findAll().get(1);
        System.out.println("galleryBoard DeleteYn : " + board.getDeleteYn());

        Throwable exception = assertThrows(EscaException.class, () ->
            galleryReplyService.getGalleryReplyList(board.getId())
        );
        assertThat(exception.getMessage()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getMessage());
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postGalleryReply() {
        GalleryBoard board = galleryRepository.findAll().get(0);
        GalleryReplyRequest request = new GalleryReplyRequest("new comment");
        galleryReplyService.postGalleryReply(board.getId(), user1.getId(), request);
        List<GalleryReply> list = galleryReplyRepository.findAll().stream()
            .filter(re -> Objects.equals(re.getGalleryBoard().getId(), board.getId())
                && Objects.equals(re.getDeleteYn(), "N"))
            .collect(Collectors.toList());
        GalleryReply reply = list.get(list.size() - 1);

        assertAll(
            () -> assertThat(list.size()).isEqualTo(3),
            () -> assertThat(reply.getComment()).isEqualTo(request.getComment()),
            () -> assertThat(reply.getUser()).isEqualTo(user1)
        );
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가한다.")
    void postGalleryReplyToDeletedBoard() {
        GalleryBoard board = galleryRepository.findAll().get(1);
        System.out.println("galleryBoard DeleteYn : " + board.getDeleteYn());
        GalleryReplyRequest request = new GalleryReplyRequest("new comment");

        Throwable exception = assertThrows(EscaException.class, () ->
            galleryReplyService.postGalleryReply(board.getId(), user1.getId(), request)
        );
        assertThat(exception.getMessage()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getMessage());
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteGalleryReply() {
        GalleryReply reply = galleryReplyRepository.findAll().get(0);
        User user = reply.getUser();
        galleryReplyService.deleteGalleryReply(reply.getId(), user.getId());
        GalleryReply delete = galleryReplyRepository.findAll().get(0);

        assertAll(
            () -> assertThat(delete.getDeleteYn()).isEqualTo("Y"),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제한다.")
    void deleteGalleryReplyByNotWriter() {
        GalleryReply reply = galleryReplyRepository.findAll().get(0);
        User user = reply.getUser().equals(user1) ? user2 : user1;

        Throwable exception = assertThrows(EscaException.class, () ->
            galleryReplyService.deleteGalleryReply(reply.getId(), user.getId())
        );
        assertThat(exception.getMessage()).isEqualTo(BoardExceptions.FORBIDDEN_DELETE_REPLY.getMessage());
    }
}