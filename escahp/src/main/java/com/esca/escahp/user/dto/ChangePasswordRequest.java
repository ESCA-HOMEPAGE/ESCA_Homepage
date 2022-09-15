package com.esca.escahp.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePasswordRequest {

	String userId;
	String oldPassword;
	String newPassword;

	public ChangePasswordRequest(String userId, String oldPassword, String newPassword){
		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
}
