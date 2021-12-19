package com.esca.escahp.service;

import com.esca.escahp.dto.StudyBoardDto;
import com.esca.escahp.mapper.StudyBoardDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class StudyBoardServiceImpl implements I_StudyBoardService {
	private final StudyBoardDao SBDao;

	@Override
	public List<StudyBoardDto> getStudyBoardList() {
		return SBDao.getStudyBoardList();
	}

	@Override
	public StudyBoardDto selectStudyBoard(Long id){
		StudyBoardDto dto = SBDao.selectStudyBoard(id);
		dto.setViewCnt(dto.getViewCnt() + 1);
		return dto;

	}

	// 게시글 추가
	@Override
	public boolean addBoard(StudyBoardDto b){
		return SBDao.addBoard(b);
	}

	@Override
	public boolean updateBoard(StudyBoardDto b) {
		return SBDao.updateBoard(b);
	}

	@Override
	public boolean deleteBoard(StudyBoardDto b) {
		return SBDao.deleteBoard(b);
	}
}
