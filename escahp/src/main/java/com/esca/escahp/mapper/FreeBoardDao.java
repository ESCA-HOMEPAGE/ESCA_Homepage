package com.esca.escahp.mapper;

import com.esca.escahp.dto.FreeBoardDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FreeBoardDao {
    public FreeBoardDto select(long id);
    public List<FreeBoardDto> selectAll();
    public long insert(FreeBoardDto dto);
    public long update(FreeBoardDto dto);
    public long delete(FreeBoardDto dto);
    // patch 조회, 신고 추가
    public void updateViewCnt(FreeBoardDto dto);
}
