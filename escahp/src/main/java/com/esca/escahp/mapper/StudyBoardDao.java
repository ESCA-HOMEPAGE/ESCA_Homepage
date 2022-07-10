package com.esca.escahp.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyBoardDao {

	public void updateViewCnt(long id);
}