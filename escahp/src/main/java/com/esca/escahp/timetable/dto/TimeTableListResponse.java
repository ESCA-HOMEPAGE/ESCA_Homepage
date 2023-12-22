package com.esca.escahp.timetable.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TimeTableListResponse {
    @ApiModelProperty(value = "지킴이 시간표 PK")
    private Long id;
    @ApiModelProperty(value = "지킴이 시간표의 학기")
    private String semester;
}
