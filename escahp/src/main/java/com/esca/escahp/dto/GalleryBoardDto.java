package com.esca.escahp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GalleryBoardDto {
    // pk
    private Long id;

    // 카테고리
    private String category;

    // 제목
    private String title;

    // 내용
    private String content;

    // 유저아이디
    private String writer;

    // 첨부파일
    private String file;

    // 작성일자
    private LocalDateTime createdAt;

    // 수정일자
    private LocalDateTime updatedAt;

    // 삭제일자
    private LocalDateTime deletedAt;

    // 삭제여부
    private String deleteYn;

    // 조회수
    private long viewCnt;

    private int likes;
}
