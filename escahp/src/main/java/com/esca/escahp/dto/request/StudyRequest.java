package com.esca.escahp.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRequest {

    @ApiModelProperty(value = "카테고리")
    private String category;

    @ApiModelProperty(value = "게시물 제목")
    private String title;

    @ApiModelProperty(value = "게시물 내용")
    private String content;

    @ApiModelProperty(value = "작성자 아이디")
    private String writer;

    @ApiModelProperty(value = "첨부파일")
    private String file;

}
