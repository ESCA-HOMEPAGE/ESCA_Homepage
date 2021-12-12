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
	@Autowired
	private StudyBoardDao SBDao;

	@Override
	public List<StudyBoardDto> getStudyBoardList() {
		return SBDao.getStudyBoardList();
	}

	@Override
	public StudyBoardDto selectStudyBoard(Long id){
		return SBDao.selectStudyBoard(id);
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
