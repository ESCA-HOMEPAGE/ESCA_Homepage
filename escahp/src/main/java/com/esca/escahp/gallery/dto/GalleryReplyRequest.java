package com.esca.escahp.gallery.dto;

import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryReply;
import com.esca.escahp.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "GalleryReplyRequest : 갤러리게시물 댓글 요청", description = "갤러리게시판의 게시물에 댓글 요청 정보를 나타낸다.")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GalleryReplyRequest {
    @ApiModelProperty(value = "댓글 내용")
    private String comment;

    public GalleryReply toEntity(GalleryBoard galleryBoard, User user) {
        return GalleryReply.builder()
            .galleryBoard(galleryBoard)
            .user(user)
            .comment(comment)
            .build();
    }
}
