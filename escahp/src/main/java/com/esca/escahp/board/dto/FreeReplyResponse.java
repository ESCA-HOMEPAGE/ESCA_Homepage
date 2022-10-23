package com.esca.escahp.board.dto;

import com.esca.escahp.board.entity.FreeReply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeReplyResponse : 자유게시물 댓글 응답", description = "자유게시판의 게시물에 댓글 응답 정보를 나타낸다.")
@Getter
@NoArgsConstructor
public class FreeReplyResponse {

    @ApiModelProperty(value = "댓글 번호(PK)")
    private Long id;

    @ApiModelProperty(value = "게시물 번호(FK)")
    private long postId;

    @ApiModelProperty(value = "댓글 작성자 닉네임")
    private String writer;

    @ApiModelProperty(value = "댓글 내용")
    private String comment;

    @ApiModelProperty(value = "작성 일자")
    private LocalDateTime createAt;

    @ApiModelProperty(value = "삭제 여부")
    private String deleteYn;

    @ApiModelProperty(value = "신고 수")
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
