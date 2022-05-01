package com.esca.escahp.domain;

import java.time.LocalDateTime;

public class StudyBoard {

    private Long id;

    private String category;

    private String title;

    private String content;

    private String writer;

    private String file;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String deleteYn;

    private long viewCnt;

    private int likes;

}
