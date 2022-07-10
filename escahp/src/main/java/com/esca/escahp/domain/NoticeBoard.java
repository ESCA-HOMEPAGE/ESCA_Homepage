package com.esca.escahp.domain;

import java.time.LocalDateTime;

public class NoticeBoard {

    private long id;

    private String title;

    private String category;

    private String writer;

    private String content;

    private String file;

    private Integer likes;

    private long viewCnt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String deleteYn;

}
