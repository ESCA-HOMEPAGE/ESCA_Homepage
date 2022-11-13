package com.esca.escahp.study.dto;

import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.entity.StudyReply;
import com.esca.escahp.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "StudyReplyRequest : 스터디게시물 댓글 요청", description = "스터디게시판의 게시물에 댓글 요청 정보를 나타낸다.")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyReplyRequest {

    @ApiModelProperty(value = "댓글 내용")
    private String comment;

    public StudyReply toEntity(StudyBoard studyBoard, User user) {
        return StudyReply.builder()
            .studyBoard(studyBoard)
            .user(user)
            .comment(comment)
            .build();
    }
}
