package com.esca.escahp.notice.dto;

import com.esca.escahp.notice.entity.NoticeBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(value = "NoticeBoard : 게시물 응답 정보", description = "공지 게시판의 게시물의 응답 정보를 나타낸다.")
public class NoticeResponse {

    @ApiModelProperty(value = "게시물 번호(PK)")
    private Long id;

    @ApiModelProperty(value = "게시물 제목")
    private String title;

    @ApiModelProperty(value = "카테고리")
    private String category;

    @ApiModelProperty(value = "작성자")
    private String writer;

    @ApiModelProperty(value = "게시물 내용")
    private String content;

    @ApiModelProperty(value = "첨부파일")
    private String file;

    @ApiModelProperty(value = "좋아요 수")
    private int likes;

    @ApiModelProperty(value = "조회수")
    private long viewCnt;

    @ApiModelProperty(value = "작성일자")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정일자")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "삭제일자")
    private LocalDateTime deletedAt;

    @ApiModelProperty(value = "삭제여부")
    private String deleteYn;

    public NoticeResponse(NoticeBoard noticeBoard) {
        this.id = noticeBoard.getId();
        this.title = noticeBoard.getTitle();
        this.category = noticeBoard.getCategory();
        this.writer = noticeBoard.getWriter();
        this.content = noticeBoard.getContent();
        this.file = noticeBoard.getFile();
        this.likes = noticeBoard.getLikes();
        this.viewCnt = noticeBoard.getViewCnt();
        this.createdAt = noticeBoard.getCreatedAt();
        this.updatedAt = noticeBoard.getUpdatedAt();
        this.deletedAt = noticeBoard.getDeletedAt();
        this.deleteYn = noticeBoard.getDeleteYn();
    }
}