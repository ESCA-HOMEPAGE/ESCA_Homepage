package com.esca.escahp.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FreeBoardDto {
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
}
