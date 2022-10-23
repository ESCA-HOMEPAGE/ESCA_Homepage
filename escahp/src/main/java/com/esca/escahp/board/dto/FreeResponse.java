package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeResponse : 게시물 응답 정보", description = "자유 게시판의 게시물 응답 정보를 나타낸다.")
@Getter
@NoArgsConstructor
public class FreeResponse {
    @ApiModelProperty(value = "게시물 번호(PK)")
    private Long id;

    @ApiModelProperty(value = "게시물 제목")
    private String title;

    @ApiModelProperty(value = "게시물 내용")
    private String content;

    @ApiModelProperty(value = "작성자 아이디")
    private String writer;

    @ApiModelProperty(value = "첨부파일")
    private String file;

    @ApiModelProperty(value = "작성일자")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정일자")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "삭제일자")
    private LocalDateTime deletedAt;

    @ApiModelProperty(value = "삭제여부")
    private String deleteYn;

    @ApiModelProperty(value = "조회수")
    private long viewCnt;

    @ApiModelProperty(value = "신고수")
    private int report;

    @ApiModelProperty(value = "좋아요 수")
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
