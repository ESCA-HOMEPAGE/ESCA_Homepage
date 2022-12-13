package com.esca.escahp.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {
	@ApiModelProperty(value = "아이디")
	private String userId;

	@ApiModelProperty(value = "비밀번호")
	private String password;

	@ApiModelProperty(value = "기수")
	private int generation;

	@ApiModelProperty(value = "닉네임")
	private String nickname;

	@ApiModelProperty(value = "이름")
	private String name;

	@ApiModelProperty(value = "등급")
	private int rank;

	@ApiModelProperty(value = "이메일")
	private String email;

	@ApiModelProperty(value = "프로필 이미지 주소")
	private String profileImg;

	@ApiModelProperty(value = "자기소개")
	private String pr;

}
