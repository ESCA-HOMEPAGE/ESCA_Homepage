package com.esca.escahp.notice.dto;

import com.esca.escahp.notice.entity.Notice;
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

    @ApiModelProperty(value = "조회수")
    private long viewCnt;

    @ApiModelProperty(value = "작성일자")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정일자")
    private LocalDateTime updatedAt;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.category = notice.getCategory();
        this.writer = notice.getWriter();
        this.content = notice.getContent();
        this.file = notice.getFile();
        this.viewCnt = notice.getViewCnt();
        this.createdAt = notice.getCreatedAt();
        this.updatedAt = notice.getUpdatedAt();
    }
}