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
@AllArgsConstructor
@ApiModel(value = "GalleryBoard : 좋아요 불러오기", description = "갤러리 게시판의 좋아요 정보를 요청한다.")
public class GalleryLikeRequest {

    @ApiModelProperty(value = "좋아요 글번호")
    private long postId;

    @ApiModelProperty(value = "좋아요 누른 사용자")
    private long userId;


    public GalleryBoardLike toEntity(GalleryBoard galleryBoard, User user){
        return GalleryBoardLike.builder()
                .galleryBoard(galleryBoard)
                .user(user)
                .build();

    }

}
