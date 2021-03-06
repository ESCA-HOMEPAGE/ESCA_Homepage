package com.esca.escahp.mapper;

import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FreeBoardDao {
    public FreeBoardDto select(long id);
    public List<FreeBoardDto> selectAll();
    public void insert(FreeBoardDto dto);
    public void update(FreeBoardDto dto);
    public void delete(FreeBoardDto dto);
    public void updateViewCnt(long id);
    public void updateReport(FreeBoardDto dto);
}
