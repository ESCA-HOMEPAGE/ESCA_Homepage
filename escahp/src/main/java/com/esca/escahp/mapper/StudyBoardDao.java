package com.esca.escahp.mapper;

import com.esca.escahp.dto.StudyBoardDto;
import java.util.*;

public interface StudyBoardDao {
	// 게시글 생성
	public int insertStudyBoard(StudyBoardDao params){

	};

	// 게시글 조회
	public StudyBoardDao selectStudyBoard(Long id){

	};

	// 게시글 수정
	public int updateStudyBoard(StudyBoardDao params){

	};

	// 게시글 삭제
	public int deleteStudyBoard(Long id){

	};

	// 게시글 목록 조회
	public List<StudyBoardDao> selectStudyBoardList(){

	};

	// 삭제 게시글 조회
	public int selectStudyBoardTotal(){

	};
}
