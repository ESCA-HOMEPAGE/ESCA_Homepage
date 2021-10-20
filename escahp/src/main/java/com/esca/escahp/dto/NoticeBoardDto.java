package com.esca.escahp.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeBoardDto {

    /* pk */
    private Long id;

    /* 제목 */
    private String title;

    /* 카테고리 */
    private String category;

    /* 작성자 */
    private String writer;

    /* 내용 */
    private String content;

    /* 첨부파일 */
    private String file;

    /* 작성일 */
    private LocalDateTime createAt;

    /* 수정일 */
    private LocalDateTime updateAt;

    /* 삭제일 */
    private LocalDateTime deleteAt;

    /* 삭제여부 */
    private String deleteYn;

}