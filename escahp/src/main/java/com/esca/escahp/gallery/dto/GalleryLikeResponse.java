package com.esca.escahp.gallery.dto;

import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryBoardLike;
import com.esca.escahp.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GalleryLikeResponse {
    @ApiModelProperty(value = "좋아요 아이디")
    private Long id;

    @ApiModelProperty(value = "좋아요 글번호")
    private Long postId;

    @ApiModelProperty(value = "좋아요 누른 사용자")
    private Long userId;


    public GalleryLikeResponse(GalleryBoardLike galleryBoardLike) {
        this.id = galleryBoardLike.getId();
        this.postId = galleryBoardLike.getGalleryBoard().getId();
        this.userId = galleryBoardLike.getUser().getId();
    }
}
