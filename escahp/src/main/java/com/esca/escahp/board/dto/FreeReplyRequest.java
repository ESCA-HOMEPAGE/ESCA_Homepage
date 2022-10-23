package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.entity.FreeReply;
import com.esca.escahp.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeReplyRequest : 자유게시물 댓글 요청", description = "자유게시판의 게시물에 댓글 요청 정보를 나타낸다.")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FreeReplyRequest {
    @ApiModelProperty(value = "댓글 내용")
    private String comment;

    public FreeReply toEntity(FreeBoard freeBoard, User user) {
        return FreeReply.builder()
            .freeBoard(freeBoard)
            .user(user)
            .comment(comment)
            .build();
    }
}
