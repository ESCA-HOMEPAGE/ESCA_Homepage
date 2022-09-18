package com.esca.escahp.timetable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"TimeTable"})
@RestController
@RequestMapping("/keeper")
@CrossOrigin(origins = {"*"})
public class TimeTableController {

    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @ApiOperation(value = "지킴이 선정 게시판을 생성한다.")
    @GetMapping
    public void getKeeperBoard() {
        return;
    }

}