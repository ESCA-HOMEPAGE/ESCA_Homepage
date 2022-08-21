package com.esca.escahp.notice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "NoticeBoard : 게시물 정보", description = "공지 게시판의 게시물의 상세 정보를 나타낸다.")
public class NoticeBoardDto {

    @ApiModelProperty(value = "게시물 번호(PK)")
    private long id;

    @ApiModelProperty(value = "게시물 제목")// 제목
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
    private Integer likes;

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

}