package com.esca.escahp.schedule.dto;

import com.esca.escahp.schedule.entity.Schedule;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

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

    @ApiModelProperty(value = "작성일자")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정일자")
    private LocalDateTime updatedAt;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.tag = schedule.getTag();
        this.content = schedule.getContent();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
