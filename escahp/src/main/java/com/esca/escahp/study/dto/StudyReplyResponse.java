package com.esca.escahp.study.dto;

import com.esca.escahp.study.entity.StudyReply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "FreeReplyResponse : 스터디게시물 댓글 응답", description = "스터디게시판의 게시물에 댓글 응답 정보를 나타낸다.")
@Getter
@NoArgsConstructor
public class StudyReplyResponse {

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

    public StudyReplyResponse(StudyReply studyReply) {
        this.id = studyReply.getId();
        this.comment = studyReply.getComment();
        this.postId = studyReply.getStudyBoard().getId();
        this.writer = studyReply.getUser().getNickname();
        this.createdAt = studyReply.getCreatedAt();
        this.deleteYn = studyReply.getDeleteYn();
        this.report = studyReply.getReport();
    }
}
