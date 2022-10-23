package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeRequest : 자유게시물 요청 정보", description = "자유게시판의 게시물 요청 정보를 나타낸다.")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FreeRequest {

    @ApiModelProperty(value = "게시물 제목")
    private String title;

    @ApiModelProperty(value = "게시물 내용")
    private String content;

    @ApiModelProperty(value = "작성자 닉네임")
    private String writer;

    @ApiModelProperty(value = "첨부파일")
    private String file;

    public FreeBoard toEntity() {
        return FreeBoard.builder()
            .title(title)
            .content(content)
            .writer(writer)
            .file(file)
            .build();
    }
}
