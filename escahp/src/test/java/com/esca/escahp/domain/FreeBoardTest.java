package com.esca.escahp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FreeBoardTest {

    private static FreeBoard board;

    @BeforeEach
    void setUp() {
        board = new FreeBoard("title", "content", "writer", "some files");
    }

    @DisplayName("자유 게시물을 삭제한다. - 실제로 삭제하지 않고 delete 정보를 수정")
    @Test
    void delete() {
        board.delete();
        assertThat(board.getDeleteYn()).isEqualTo("Y");
    }

    @DisplayName("자유 게시물을 수정한다.")
    @Test
    void update() {
        String updateTitle = "updated title";
        String updatedContent = "updated content";
        String updatedFiles = "updated files";
        board.update(updateTitle, updatedContent, updatedFiles);
        assertAll(
            () -> assertThat(board.getTitle()).isEqualTo(updateTitle),
            () -> assertThat(board.getContent()).isEqualTo(updatedContent),
            () -> assertThat(board.getFile()).isEqualTo(updatedFiles)
        );
    }

    @DisplayName("자유 게시물 신고 수를 더한다.")
    @Test
    void updateReport() {
        assertThat(board.getReport()).isEqualTo(0);
        board.updateReport();
        assertThat(board.getReport()).isEqualTo(1);
    }

    @DisplayName("자유 게시물 신고수가 5회가 넘을 시 제목을 블라인드 처리한다.")
    @Test
    void blindTitle() {
        board.mosaicTitle();
        assertThat(board.getTitle()).isEqualTo("title");
        for (int i = 0; i < 5; i++) {
            board.updateReport();
        }
        board.mosaicTitle();
        assertThat(board.getTitle()).isEqualTo("블라인드된 글입니다.");
    }

    @DisplayName("자유 게시물 조회 수를 더한다.")
    @Test
    void updateViewCount() {
        assertThat(board.getViewCnt()).isEqualTo(0);
        board.addViewCount();
        assertThat(board.getViewCnt()).isEqualTo(1);
    }
}