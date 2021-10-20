package com.esca.escahp.mapper;

import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;


public interface FreeBoardDao {
    public FreeBoardDto select(long id);
    public List<FreeBoardDto> selectAll();
    public long insert(FreeBoardDto dto);
    public long update(FreeBoardDto dto);
    public long delete(long id);
}
