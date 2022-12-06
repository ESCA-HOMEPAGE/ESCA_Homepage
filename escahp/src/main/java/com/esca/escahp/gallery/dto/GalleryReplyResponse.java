package com.esca.escahp.gallery.dto;

import com.esca.escahp.gallery.entity.GalleryReply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeReplyResponse : 갤러리게시물 댓글 응답", description = "갤러리게시판의 게시물에 댓글 응답 정보를 나타낸다.")
@Getter
@NoArgsConstructor
public class GalleryReplyResponse {
    @ApiModelProperty(value = "댓글 번호(PK)")
    private Long id;

    @ApiModelProperty(value = "게시물 번호(FK)")
    private long postId;

    @ApiModelProperty(value = "댓글 작성자 닉네임")
    private String writer;

    @ApiModelProperty(value = "댓글 내용")
    private String comment;

    @ApiModelProperty(value = "작성 일자")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "삭제 여부")
    private String deleteYn;

    @ApiModelProperty(value = "신고 수")
    private int report;

    public GalleryReplyResponse(GalleryReply galleryReply) {
        this.id = galleryReply.getId();
        this.postId = galleryReply.getGalleryBoard().getId();
        this.writer = galleryReply.getUser().getNickname();
        this.createdAt = galleryReply.getCreatedAt();
        this.deleteYn = galleryReply.getDeleteYn();
        this.report = galleryReply.getReport();
    }
}
