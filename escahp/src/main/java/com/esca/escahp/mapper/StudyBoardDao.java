package com.esca.escahp.mapper;

import com.esca.escahp.dto.StudyBoardDto;
import java.util.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyBoardDao {

	public List<StudyBoardDto> getStudyBoardList();

	public StudyBoardDto selectStudyBoard(long id);

	public void addBoard(StudyBoardDto b);

	public void updateBoard(StudyBoardDto b);

	public void deleteBoard(StudyBoardDto b);

	public void updateViewCnt(long id);
}