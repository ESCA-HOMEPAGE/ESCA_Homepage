package com.esca.escahp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private int id;
	private String user_id;
	private String password;
	private int generation;
	private String nickname;
	private String name;
	private String phone;
	private int rank;
}
