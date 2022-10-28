package com.esca.escahp.study;

import static com.esca.escahp.DataLoader.user1;
import static org.junit.jupiter.api.Assertions.*;

import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.entity.StudyReply;
import com.esca.escahp.study.repository.StudyReplyRepository;
import com.esca.escahp.study.repository.StudyRepository;
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
    private StudyRepository studyRepository;

    @Autowired
    private StudyReplyRepository studyReplyRepository;

    @BeforeEach
    void before() {
        StudyBoard studyBoard = new StudyBoard("title1", "content1", "nickOne", "2022 여름", "example1.jpg");
        StudyBoard deletedBoard = new StudyBoard("title1", "content1", "nickOne", "2022 여름", "example1.jpg");
        StudyReply studyReply1 = new StudyReply(studyBoard, user1, "comment1");
        StudyReply studyReply2 = new StudyReply(studyBoard, user1, "comment2");
        StudyReply studyReply3 = new StudyReply(studyBoard, user1, "comment3");
        StudyReply studyReply4 = new StudyReply(deletedBoard, user1, "comment4");
        deletedBoard.delete();
        studyReply2.delete();
        studyRepository.save(studyBoard);
        studyRepository.save(deletedBoard);
        studyReplyRepository.save(studyReply1);
        studyReplyRepository.save(studyReply2);
        studyReplyRepository.save(studyReply3);
        studyReplyRepository.save(studyReply4);
    }

    @AfterEach
    void after() {
        studyReplyRepository.deleteAll();
        studyRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판의 댓글 목록을 가져온다.")
    void getStudyReplyList() {
        // TODO : 서비스 완성 후 작성
    }

    @Test
    @DisplayName("삭제된 게시판의 댓글 목록을 가져온다.")
    void getStudyReplyListFromDeletedBoard() {
        // TODO : 서비스 완성 후 작성
    }

    @Test
    @DisplayName("게시판에 댓글을 추가한다.")
    void postStudyReply() {
        // TODO : 서비스 완성 후 작성
    }

    @Test
    @DisplayName("삭제된 게시판에 댓글을 추가한다.")
    void postStudyReplyFromDeletedBoard() {
        // TODO : 서비스 완성 후 작성
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteStudyReply() {
        // TODO : 서비스 완성 후 작성
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 댓글을 삭제한다.")
    void deleteStudyReplyFromNotWriter() {
        // TODO : 서비스 완성 후 작성
    }
}