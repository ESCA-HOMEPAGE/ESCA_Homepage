package com.esca.escahp.gallery.dto;

import com.esca.escahp.gallery.entity.GalleryBoard;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GalleryResponse {
    @ApiModelProperty(value = "게시물 번호(PK)")
    private Long id;

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

    public GalleryResponse(GalleryBoard galleryBoard){
        this.id = galleryBoard.getId();
        this.category = galleryBoard.getCategory();
        this.title = galleryBoard.getTitle();
        this.content = galleryBoard.getContent();
        this.writer = galleryBoard.getWriter();
        this.file = galleryBoard.getFile();
        this.createdAt = galleryBoard.getCreatedAt();
        this.updatedAt = galleryBoard.getUpdatedAt();
        this.deletedAt = galleryBoard.getDeletedAt();
        this.deleteYn = galleryBoard.getDeleteYn();
        this.viewCnt = galleryBoard.getViewCnt();
    }
}
