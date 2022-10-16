package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeReply;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FreeReplyResponse {

    private Long id;

    private long postId;

    private String writer;

    private String comment;

    private LocalDateTime createAt;

    private String deleteYn;

    private int report;

    public FreeReplyResponse(FreeReply freeReply) {
        this.id = freeReply.getId();
        this.comment = freeReply.getComment();
        this.postId = freeReply.getFreeBoard().getId();
        this.writer = freeReply.getUser().getNickname();
        this.createAt = freeReply.getCreatedAt();
        this.deleteYn = freeReply.getDeleteYn();
        this.report = freeReply.getReport();
    }
}
