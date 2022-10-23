package com.esca.escahp.board;

import static com.esca.escahp.DataLoader.user1;
import static com.esca.escahp.DataLoader.user2;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.board.dto.FreeReplyRequest;
import com.esca.escahp.board.dto.FreeReplyResponse;
import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.entity.FreeReply;
import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.board.repository.FreeReplyRepository;
import com.esca.escahp.board.repository.FreeRepository;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@ActiveProfiles("test")
class FreeReplyServiceTest {

    @Autowired
    private FreeReplyService freeReplyService;
    @Autowired
    private FreeRepository freeRepository;
    @Autowired
    private FreeReplyRepository freeReplyRepository;


    @BeforeEach
    void before() {
        FreeBoard freeBoard = new FreeBoard("title1", "content1", "nickOne", "example1.jpg");
        FreeBoard deletedBoard = new FreeBoard("title2", "content2", "nickTwo", "example2.jpg");
        FreeBoard blindedBoard = new FreeBoard("blind title", "blind content", "nickOne", "example.jpg", 5);
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
    void getFreeReplyList() {
        FreeBoard board = freeRepository.findAll().get(0);
        List<FreeReplyResponse> list = freeReplyService.getFreeReplyList(board.getId());

        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getFreeReplyListFromDeleteBoard() {
        FreeBoard board = freeRepository.findAll().get(1);
        System.out.println("freeBoard DeleteYn : " + board.getDeleteYn());

        Throwable exception = assertThrows(EscaException.class, () ->
            freeReplyService.getFreeReplyList(board.getId())
        );
        assertEquals(BoardExceptions.NOT_FOUND_BOARD.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("블라인드된 게시판의 댓글 목록을 가져온다.")
    void getFreeReplyListFromBlindedBoard() {
        FreeBoard board = freeRepository.findAll().get(2);
        System.out.println("freeBoard reportCount : " + board.getReport());

        Throwable exception = assertThrows(EscaException.class, () ->
            freeReplyService.getFreeReplyList(board.getId())
        );
        assertEquals(BoardExceptions.NOT_FOUND_BOARD.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postFreeReply() {
        FreeBoard board = freeRepository.findAll().get(0);
        FreeReplyRequest request = new FreeReplyRequest("add comment");
        freeReplyService.postFreeReply(board.getId(), user1.getId(), request);
        List<FreeReplyResponse> list = freeReplyService.getFreeReplyList(board.getId());
        FreeReplyResponse response = list.get(list.size() - 1);

        assertAll(
            () -> assertEquals(4, list.size()),
            () -> assertEquals(request.getComment(), response.getComment()),
            () -> assertEquals(user1.getNickname(), response.getWriter())
        );
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가하려한다.")
    void postFreeReplyFromDeleteBoard() {
        FreeBoard board = freeRepository.findAll().get(1);
        System.out.println("freeBoard DeleteYn : " + board.getDeleteYn());
        FreeReplyRequest request = new FreeReplyRequest("add comment");

        Throwable exception = assertThrows(EscaException.class, () ->
            freeReplyService.postFreeReply(board.getId(), user1.getId(), request)
        );
        assertEquals(BoardExceptions.NOT_FOUND_BOARD.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("블라인드된 게시판에 댓글을 추가하려한다.")
    void postFreeReplyFromBlindedBoard() {
        FreeBoard board = freeRepository.findAll().get(2);
        System.out.println("freeBoard reportCount : " + board.getReport());
        FreeReplyRequest request = new FreeReplyRequest("add comment");

        Throwable exception = assertThrows(EscaException.class, () ->
            freeReplyService.postFreeReply(board.getId(), user1.getId(), request)
        );
        assertEquals(BoardExceptions.NOT_FOUND_BOARD.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteFreeReply() {
        FreeReply reply = freeReplyRepository.findAll().get(0);
        User user = reply.getUser();
        freeReplyService.deleteFreeReply(reply.getId(), user.getId());
        FreeReply delete = freeReplyRepository.findAll().get(0);

        assertAll(
            () -> assertEquals("Y", delete.getDeleteYn()),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제하려한다.")
    void deleteFreeReplyFromNotWriter() {
        FreeReply reply = freeReplyRepository.findAll().get(0);
        User user;
        if (reply.getUser().equals(user1)) {
            user = user2;
        } else {
            user = user1;
        }

        Throwable exception = assertThrows(EscaException.class, () ->
            freeReplyService.deleteFreeReply(reply.getId(), user.getId())
        );
        assertEquals(BoardExceptions.FORBIDDEN_DELETE_REPLY.getMessage(), exception.getMessage());
    }
}