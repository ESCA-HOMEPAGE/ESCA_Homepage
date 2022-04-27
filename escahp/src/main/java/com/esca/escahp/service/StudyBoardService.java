package com.esca.escahp.service;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.mapper.StudyBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyBoardService implements I_StudyBoardService {
	private final StudyBoardDao SBDao;

	@Override
	public List<StudyBoardDto> getStudyBoardList() {
		return SBDao.getStudyBoardList();
	}

	@Override
	public StudyBoardDto selectStudyBoard(long id){
		StudyBoardDto dto = SBDao.selectStudyBoard(id);
		SBDao.updateViewCnt(id);
		dto.setViewCnt(dto.getViewCnt() + 1);
		return dto;

	}

	// 게시글 추가
	@Override
	public void addBoard(StudyBoardDto b){ SBDao.addBoard(b); }

	@Override
	public void updateBoard(StudyBoardDto b) {
		SBDao.updateBoard(b);
	}

	@Override
	public void deleteBoard(StudyBoardDto b) {
		SBDao.deleteBoard(b);
	}

	public void updateViewCnt(long id) { SBDao.updateViewCnt(id); }
}
