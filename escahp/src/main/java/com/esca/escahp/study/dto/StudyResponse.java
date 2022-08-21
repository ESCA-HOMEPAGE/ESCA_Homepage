package com.esca.escahp.study.dto;

import com.esca.escahp.study.entity.StudyBoard;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyResponse {

    @ApiModelProperty(value = "게시물 번호(PK)")
    private Long id;

    @ApiModelProperty(value = "카테고리")
    private String category;

    @ApiModelProperty(value = "게시물 제목")
    private String title;

    @ApiModelProperty(value = "게시물 내용")
    private String content;

    @ApiModelProperty(value = "작성자 아이디")
    private String writer;

    @ApiModelProperty(value = "첨부파일")
    private String file;

    @ApiModelProperty(value = "작성일자")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정일자")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "삭제일자")
    private LocalDateTime deletedAt;

    @ApiModelProperty(value = "삭제여부")
    private String deleteYn;

    @ApiModelProperty(value = "조회수")
    private long viewCnt;

    @ApiModelProperty(value = "좋아요 수")
    private int likes;

    public StudyResponse(StudyBoard studyBoard) {
        this.id = studyBoard.getId();
        this.category = studyBoard.getCategory();
        this.title = studyBoard.getTitle();
        this.content = studyBoard.getContent();
        this.writer = studyBoard.getWriter();
        this.file = studyBoard.getFile();
        this.createdAt = studyBoard.getCreatedAt();
        this.updatedAt = studyBoard.getUpdatedAt();
        this.deletedAt = studyBoard.getDeletedAt();
        this.deleteYn = studyBoard.getDeleteYn();
        this.viewCnt = studyBoard.getViewCnt();
        this.likes = studyBoard.getLikes();
    }

}
