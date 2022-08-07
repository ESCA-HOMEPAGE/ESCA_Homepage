package com.esca.escahp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel(value = "FreeBoard : 게시물 정보", description = "자유 게시판의 게시물의 상세 정보를 나타낸다.")
public class FreeBoardDto {

    @ApiModelProperty(value = "게시물 번호(PK)")
    private long id;

    @ApiModelProperty(value = "게시물 제목")
    private String title;

    @ApiModelProperty(value = "게시물 내용")
    private String content;

    @ApiModelProperty(value = "작성자")
    private String writer;

    @ApiModelProperty(value = "첨부파일")
    private String file;

    @ApiModelProperty(value = "작성일자")
    private LocalDate createdAt;

    @ApiModelProperty(value = "수정일자")
    private LocalDate updatedAt;

    @ApiModelProperty(value = "삭제일자")
    private LocalDate deletedAt;

    @ApiModelProperty(value = "삭제여부")
    private String deleteYn;

    @ApiModelProperty(value = "조회수")
    private int viewCnt;

    @ApiModelProperty(value = "신고 횟수")
    private int report;

    @ApiModelProperty(value = "좋아요 수")
    private int likes;
}
