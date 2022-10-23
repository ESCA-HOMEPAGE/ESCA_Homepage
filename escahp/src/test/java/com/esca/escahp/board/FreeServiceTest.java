package com.esca.escahp.board;

import com.esca.escahp.board.dto.FreeRequest;
import com.esca.escahp.board.dto.FreeResponse;
import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.repository.FreeRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
public class FreeServiceTest {
    @Autowired
    private FreeService freeService;

    @Autowired
    private FreeRepository freeRepository;


     @BeforeEach
     void before() {
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
    @DisplayName("자유게시물들을 가져온다.")
    void getFreeBoardList() {
        List<FreeResponse> list = freeService.getFreeBoardList();

        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("자유게시물 하나를 가져온다.")
    void selectFreeBoard() {
        FreeResponse free = freeService.getFreeBoardList().get(0);
        FreeResponse selectFree = freeService.selectFreeBoard(free.getId());

        assertEquals(free.getTitle(), selectFree.getTitle());
    }

    @Test
    @DisplayName("존재하지 않는 자유게시물을 가져오려 한다.")
    void selectNotAccessFreeBoard() {
         // -1 id로 접근하려한다. 이 경우는 예외처리를 다시 작성한 뒤 작성하겠다.
    }

    @Test
    @DisplayName("블라인드 처리된 자유게시물을 가져오려 한다.")
    void selectBlindedFreeBoard() {
        // 이 경우는 예외처리를 다시 작성한 뒤 작성하겠다.
    }

    @Test
    @DisplayName("자유게시판에 게시물을 추가한다.")
    void postBoard() {
        FreeRequest request = new FreeRequest("new title", "new content", "new writer", "new file.jpg");
        long id = freeService.postBoard(request);
        FreeResponse response = freeService.selectFreeBoard(id);

        assertAll(
            () -> assertNotNull(response.getId()),
            () -> assertEquals(request.getTitle(), response.getTitle()),
            () -> assertEquals(request.getContent(), response.getContent()),
            () -> assertEquals(request.getWriter(), response.getWriter()),
            () -> assertEquals(request.getFile(), response.getFile())
        );
    }

    @Test
    @DisplayName("자유게시판 게시물을 수정한다.")
    void updateBoard() {
         FreeResponse oldFree = freeService.getFreeBoardList().get(0);
         FreeRequest newFree = new FreeRequest("updateTitle", "updateContent", oldFree.getWriter(), "updateExample.jpg");
         freeService.updateBoard(oldFree.getId(), newFree);
         FreeResponse response = freeService.selectFreeBoard(oldFree.getId());

        assertAll(
            () -> assertEquals(oldFree.getId(), response.getId()),
            () -> assertEquals(newFree.getTitle(), response.getTitle()),
            () -> assertEquals(newFree.getContent(), response.getContent()),
            () -> assertEquals(newFree.getWriter(), response.getWriter()),
            () -> assertEquals(newFree.getFile(), response.getFile())
        );

    }

    @Test
    @DisplayName("자유게시판 게시물을 삭제한다.")
    void deleteBoard() {
        FreeResponse response = freeService.getFreeBoardList().get(0);
        freeService.deleteBoard(response.getId());
        FreeResponse deleteResponse = freeService.selectFreeBoard(response.getId());

        assertAll(
            () -> assertEquals(deleteResponse.getDeleteYn(), "Y"),
            () -> assertNotNull(deleteResponse.getDeletedAt())
        );
    }

    @Test
    @DisplayName("조회수가 증가한다.")
    void increaseViewCnt() {
        FreeResponse oldResponse = freeService.getFreeBoardList().get(0);
        freeService.increaseViewCnt(oldResponse.getId());
        FreeResponse newResponse = freeService.selectFreeBoard(oldResponse.getId());

        assertAll(
            () -> assertEquals(oldResponse.getId(), newResponse.getId()),
            () -> assertEquals(oldResponse.getViewCnt()+1, newResponse.getViewCnt())
        );
    }
}
