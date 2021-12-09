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
	private LocalDateTime createdAt;

	// 수정일자
	private LocalDateTime updatedAt;

	// 삭제일자
	private LocalDateTime deleteAt;

	// 삭제여부
	private String delete_yn;

	// 조회수
	private Long view_cnt;

	// 유저아이디
	private Long user_id;

	public StudyBoardDto(long id, String category, String title, String content,
		String file, LocalDateTime createdAt, Long view_cnt, Long user_id){
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.file = file;
		this.createdAt = createdAt;
		this.view_cnt = view_cnt;
		this.user_id = user_id;
	}
}
