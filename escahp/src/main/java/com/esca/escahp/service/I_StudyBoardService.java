package com.esca.escahp.service;


import com.esca.escahp.dto.StudyBoardDto;
import java.util.List;

public interface I_StudyBoardService {
	public List<StudyBoardDto> getStudyBoardList();

	public StudyBoardDto selectStudyBoard(Long id);

	public boolean addBoard(StudyBoardDto b);

	public boolean updateBoard(StudyBoardDto b);

	public boolean deleteBoard(StudyBoardDto b);
}