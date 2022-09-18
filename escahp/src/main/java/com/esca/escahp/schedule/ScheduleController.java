package com.esca.escahp.schedule;

import com.esca.escahp.schedule.dto.ScheduleRequest;
import com.esca.escahp.schedule.dto.ScheduleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Schedule"})
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
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        List<ScheduleResponse> responses = scheduleService.getScheduleList();
        return ResponseEntity.ok(responses);
    }

    @ApiOperation(value = "특정 연간일정 상세 정보 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long id) {
        ScheduleResponse response = scheduleService.selectSchedule(id);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "연간일정 등록")
    @PostMapping
    public ResponseEntity<ScheduleResponse> insertSchedule(@RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleService.addSchedule(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "연간일정 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleService.updateSchedule(id, request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "연간일정 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

}
