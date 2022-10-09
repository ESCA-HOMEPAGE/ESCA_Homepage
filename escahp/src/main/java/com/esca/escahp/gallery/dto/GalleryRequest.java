package com.esca.escahp.gallery.dto;

import com.esca.escahp.gallery.entity.GalleryBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GalleryBoard : 게시물 요청 정보", description = "갤러리 게시판의 게시물 요청 정보를 나타낸다.")
public class GalleryRequest {

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

    public GalleryBoard toEntity(){
        return GalleryBoard.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .file(file)
                .build();
    }
}
