package com.esca.escahp.timetable;

import com.esca.escahp.timetable.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"TimeTable"})
@RestController
@RequestMapping("/time-tables")
@CrossOrigin(origins = {"*"})
public class TimeTableController {

    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @ApiOperation(value = "지킴이 게시판 목록을 가져온다")
    @GetMapping
    public ResponseEntity<List<TimeTableListResponse>> getTimeTableList(){
        return null;
    };

    @ApiOperation(value = "특정 지킴이 게시판을 가져온다")
    @GetMapping("/{id}")
    public ResponseEntity<TimeTableResponse> selectTimeTable(@PathVariable Long id){
        return null;
    };

    @ApiOperation(value = "지킴이 게시판을 생성한다.")
    @PostMapping
    public ResponseEntity<TimeTableResponse> addTimeTable(@RequestBody TimeTableRequest request){
        return null;
    };

    @ApiOperation(value = "특정 지킴이 게시판을 업데이트한다")
    @PatchMapping("/{id}")
    public ResponseEntity<TimeTableResponse> updateTimeTable(@PathVariable Long id, @RequestBody TimeTableRequest request){
        return null;
    };

    @ApiOperation(value = "특정 지킴이 게시판을 삭제한다")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTimeTable(@PathVariable Long id){
        return null;
    };

    @ApiOperation(value = "지킴이를 신청한다")
    @PostMapping("/{userId}")
    public ResponseEntity<KeeperResponse> addKeeper(@RequestBody KeeperRequest request){
        return null;
    };

    @ApiOperation(value = "신청한 지킴이 목록을 업데이트한다")
    @PatchMapping("/{userId}/{id}")
    public ResponseEntity<KeeperResponse> updateKeeper(@PathVariable Long id, @RequestBody KeeperRequest request){
        return null;
    };

    @ApiOperation(value = "신청한 지킴이를 삭제한다")
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Object> deleteKeeper(@PathVariable Long id){
        return null;
    };

    @ApiOperation(value = "지킴이 시간표 공개 여부를 바꾼다")
    @PatchMapping("/{id}/show")
    public ResponseEntity<Object> changeShowStatus(@PathVariable Long id){
        return null;
    };
}