package com.esca.escahp.service;


import com.esca.escahp.dto.StudyBoardDto;
import java.util.List;

public interface I_StudyBoardService {
	public List<StudyBoardDto> getStudyBoardList();

	public StudyBoardDto selectStudyBoard(long id);

	public void addBoard(StudyBoardDto b);

	public void updateBoard(StudyBoardDto b);

	public void deleteBoard(StudyBoardDto b);

	public void updateViewCnt(long id);
}