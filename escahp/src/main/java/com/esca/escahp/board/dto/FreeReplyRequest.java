package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeBoard;
import com.esca.escahp.board.entity.FreeReply;
import com.esca.escahp.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FreeReplyRequest {
    private String comment;

    public FreeReply toEntity(FreeBoard freeBoard, User user) {
        return FreeReply.builder()
            .freeBoard(freeBoard)
            .user(user)
            .comment(comment)
            .build();
    }
}
