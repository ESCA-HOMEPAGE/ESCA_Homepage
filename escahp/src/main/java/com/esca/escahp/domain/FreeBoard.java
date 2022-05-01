package com.esca.escahp.domain;

import java.time.LocalDate;

public class FreeBoard {
    private long id;

    private String title;

    private String content;

    private String writer;

    private String file;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deletedAt;

    private String deleteYn;

    private int viewCnt;

    private int report;

    private int likes;
}
