package com.esca.escahp.mapper;

import com.esca.escahp.domain.FreeBoard;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FreeBoardDao {
    public FreeBoard select(long id);
    public List<FreeBoard> selectAll();
    public void insert(FreeBoard board);
    public void update(FreeBoard board);
    public void delete(FreeBoard board);
    public void updateViewCnt(long id);
    public void updateReport(FreeBoard board);
}
