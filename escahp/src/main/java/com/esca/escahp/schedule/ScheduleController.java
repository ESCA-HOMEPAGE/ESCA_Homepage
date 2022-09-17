package com.esca.escahp.schedule;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = {"*"})
public class ScheduleController {

    private final I_ScheduleService scheduleService;

    public ScheduleController(I_ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ApiOperation(value = "전체 연간일정 조회")
    @GetMapping()
    public void getAllSchedules() {
        return;
    }

    @ApiOperation(value = "특정 연간일정 상세 정보 조회")
    @GetMapping("/{id}")
    public void getSchedule(@PathVariable long id) {
        return;
    }

    @ApiOperation(value = "연간일정 등록")
    @PostMapping
    public void insertSchedule() {
        return;
    }

    @ApiOperation(value = "연간일정 수정")
    @PutMapping("/{id}")
    public void updateSchedule() {
        return;
    }

    @ApiOperation(value = "연간일정 삭제")
    @DeleteMapping("/{id}")
    public void deleteSchedule() {
        return;
    }

}
