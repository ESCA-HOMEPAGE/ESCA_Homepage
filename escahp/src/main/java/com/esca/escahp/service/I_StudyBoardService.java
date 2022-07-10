package com.esca.escahp.service;


import com.esca.escahp.domain.StudyBoard;
import com.esca.escahp.dto.response.StudyResponse;
import java.util.List;

public interface I_StudyBoardService {
	public List<StudyResponse> getStudyBoardList();

	public StudyResponse selectStudyBoard(long id);

	public void addBoard(StudyBoard b);

	public void updateBoard(StudyBoard b);

	public void deleteBoard(StudyBoard b);

	public void updateViewCnt(long id);
}