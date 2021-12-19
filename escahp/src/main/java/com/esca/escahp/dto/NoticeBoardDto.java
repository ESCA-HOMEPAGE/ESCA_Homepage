package com.esca.escahp.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeBoardDto {

    // pk
    private Long id;

    // 제목
    private String title;

    // 카테고리
    private String category;

    // 작성자
    private String writer;

    // 내용
    private String content;

    // 첨부파일
    private String file;

    // 좋아요
    private Integer likes;

    // 조회수
    private long viewCnt;

    // 작성일
    private LocalDateTime createdAt;

    // 수정일
    private LocalDateTime updatedAt;

    // 삭제일
    private LocalDateTime deletedAt;

    // 삭제여부
    private String deleteYn;

}