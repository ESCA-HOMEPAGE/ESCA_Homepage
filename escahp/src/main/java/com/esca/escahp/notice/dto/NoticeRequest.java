package com.esca.escahp.notice.dto;

import com.esca.escahp.notice.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@ApiModel(value = "NoticeBoard : 게시물 요청 정보", description = "공지 게시판의 게시물의 요청 정보를 나타낸다.")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequest {

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

    public Notice toEntity() {
        return Notice.builder()
            .title(title)
            .content(content)
            .writer(writer)
            .category(category)
            .file(file)
            .build();
    }
}