package com.esca.escahp.user.dto;

import com.esca.escahp.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthResponse {

	@ApiModelProperty(value = "사용자 ID(PK)")
	private Long id;

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

	public AuthResponse(User auth){
		this.id = auth.getId();
		this.userId = auth.getUserId();
		this.password = auth.getPassword();
		this.generation = auth.getGeneration();
		this.nickname = auth.getNickname();
		this.name = auth.getName();
		this.rank = auth.getRank();
		this.email = auth.getEmail();
		this.profileImg = auth.getProfileImg();
		this.pr = auth.getPr();
	}
}
