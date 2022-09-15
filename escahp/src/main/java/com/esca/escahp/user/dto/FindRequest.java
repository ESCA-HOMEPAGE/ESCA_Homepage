package com.esca.escahp.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindRequest {
	String name;
	String email;

	public FindRequest(String name, String email){
		this.name = name;
		this.email = email;
	}
}
