package com.esca.escahp.schedule.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {

    @ApiModelProperty(value = "일정 제목")
    private String title;

    @ApiModelProperty(value = "일정 태그")
    private String tag;

    @ApiModelProperty(value = "일정 내용")
    private String content;

    @ApiModelProperty(value = "일정 시작 일자")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "일정 종료 일자")
    private LocalDateTime endDate;
}
