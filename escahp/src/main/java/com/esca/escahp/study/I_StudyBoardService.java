package com.esca.escahp.study;


import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.dto.StudyResponse;
import java.util.List;

public interface I_StudyBoardService {
	public List<StudyResponse> getStudyBoardList();

	public StudyResponse selectStudyBoard(long id);

	public void addBoard(StudyBoard b);

	public void updateBoard(StudyBoard b);

	public void deleteBoard(StudyBoard b);

	public void updateViewCnt(long id);
}