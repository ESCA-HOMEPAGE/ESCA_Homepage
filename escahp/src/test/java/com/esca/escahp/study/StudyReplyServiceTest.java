package com.esca.escahp.study;

import static com.esca.escahp.DataLoader.user1;
import static com.esca.escahp.DataLoader.user2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.study.dto.StudyReplyRequest;
import com.esca.escahp.study.dto.StudyReplyResponse;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.entity.StudyReply;
import com.esca.escahp.study.repository.StudyReplyRepository;
import com.esca.escahp.study.repository.StudyRepository;
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
class StudyReplyServiceTest {

    @Autowired
    private StudyReplyService studyReplyService;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyReplyRepository studyReplyRepository;

    @BeforeEach
    void before() {
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
    void getStudyReplyList() {
        StudyBoard board = studyRepository.findAll().get(0);
        List<StudyReplyResponse> list = studyReplyService.getStudyReplyList(board.getId());

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getStudyReplyListFromDeletedBoard() {
        StudyBoard board = studyRepository.findAll().get(1);
        System.out.println("studyBoard DeleteYn : " + board.getDeleteYn());

        Throwable exception = assertThrows(EscaException.class, () ->
            studyReplyService.getStudyReplyList(board.getId())
        );
        assertThat(exception.getMessage()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getMessage());
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postStudyReply() {
        StudyBoard board = studyRepository.findAll().get(0);
        StudyReplyRequest request = new StudyReplyRequest("add comment");
        studyReplyService.postStudyReply(board.getId(), user1.getId(), request);
        List<StudyReply> list = studyReplyRepository.findAll().stream()
            .filter(re -> Objects.equals(re.getStudyBoard().getId(), board.getId())
                && Objects.equals(re.getDeleteYn(), "N"))
            .collect(Collectors.toList());
        StudyReply reply = list.get(list.size() - 1);

        assertAll(
            () -> assertThat(list.size()).isEqualTo(3),
            () -> assertThat(reply.getComment()).isEqualTo(request.getComment()),
            () -> assertThat(reply.getUser()).isEqualTo(user1)
        );
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가한다.")
    void postStudyReplyFromDeletedBoard() {
        StudyBoard board = studyRepository.findAll().get(1);
        System.out.println("studyBoard DeleteYn : " + board.getDeleteYn());
        StudyReplyRequest request = new StudyReplyRequest("add comment");

        Throwable exception = assertThrows(EscaException.class, () ->
            studyReplyService.postStudyReply(board.getId(), user1.getId(), request)
        );
        assertThat(exception.getMessage()).isEqualTo(BoardExceptions.NOT_FOUND_BOARD.getMessage());
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteStudyReply() {
        StudyReply reply = studyReplyRepository.findAll().get(0);
        User user = reply.getUser();
        studyReplyService.deleteStudyReply(reply.getId(), user.getId());
        StudyReply delete = studyReplyRepository.findAll().get(0);

        assertAll(
            () -> assertThat(delete.getDeleteYn()).isEqualTo("Y"),
            () -> assertNotNull(delete.getDeletedAt())
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제한다.")
    void deleteStudyReplyFromNotWriter() {
        StudyReply reply = studyReplyRepository.findAll().get(0);
        User user;
        if (reply.getUser().equals(user1)) {
            user = user2;
        } else {
            user = user1;
        }

        Throwable exception = assertThrows(EscaException.class, () ->
            studyReplyService.deleteStudyReply(reply.getId(), user.getId())
        );
        assertThat(exception.getMessage()).isEqualTo(BoardExceptions.FORBIDDEN_DELETE_REPLY.getMessage());
    }
}