package com.esca.escahp.timetable;

import com.esca.escahp.timetable.dto.*;

import java.util.List;

public interface I_TimeTableService {
    List<TimeTableListResponse> getTimeTableList();
    TimeTableResponse selectTimeTable(long id);
    TimeTableResponse addTimeTable(TimeTableRequest request);
    TimeTableResponse updateTimeTable(long id, TimeTableRequest request);
    void deleteTimeTable(long id);
    KeeperResponse addKeeper(KeeperRequest request);
    KeeperResponse updateKeeper(long id, KeeperRequest request);
    void deleteKeeper(long id);
    void changeShowStatus(long id);
    
}