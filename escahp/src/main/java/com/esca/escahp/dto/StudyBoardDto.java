package com.esca.escahp.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyBoardDto {
	// pk
	private Long id;

	// 카테고리
	private String category;

	// 제목
	private String title;

	// 작성자
	private String writer;

	// 내용
	private String content;

	// 첨부파일
	private String file;

	// 작성일자
	private LocalDateTime createAt;

	// 수정일자
	private LocalDateTime updateAt;

	// 삭제일자
	private LocalDateTime deleteAt;

	// 삭제여부
	private String deleteYn;

	// 조회수
	private Long view;

	// 유저아이디
	private Long userId;
}
