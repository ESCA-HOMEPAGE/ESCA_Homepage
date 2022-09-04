package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeBoard;
import io.swagger.annotations.ApiModel;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeBoard : 게시물 응답 정보", description = "자유 게시판의 게시물 응답 정보를 나타낸다.")
@Getter
@NoArgsConstructor
public class FreeResponse {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private String file;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String deleteYn;

    private long viewCnt;

    private int report;

    private int likes;

    public FreeResponse(FreeBoard freeBoard) {
        this.id = freeBoard.getId();
        this.title = freeBoard.mosaicTitle();
        this.content = freeBoard.getContent();
        this.writer = freeBoard.getWriter();
        this.file = freeBoard.getFile();
        this.createdAt = freeBoard.getCreatedAt();
        this.updatedAt = freeBoard.getUpdatedAt();
        this.deletedAt = freeBoard.getDeletedAt();
        this.deleteYn = freeBoard.getDeleteYn();
        this.viewCnt = freeBoard.getViewCnt();
        this.report = freeBoard.getReport();
        this.likes = freeBoard.getLikes();
    }
}
