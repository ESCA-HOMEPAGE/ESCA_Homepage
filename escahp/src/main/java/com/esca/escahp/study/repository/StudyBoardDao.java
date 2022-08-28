package com.esca.escahp.study.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyBoardDao {

	public void updateViewCnt(long id);
}