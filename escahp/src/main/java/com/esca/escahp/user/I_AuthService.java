package com.esca.escahp.user;

import com.esca.escahp.user.dto.LoginRequest;
import com.esca.escahp.user.entity.User;

public interface I_AuthService {

	User checkUserId(String userId);

	User addUser(User auth);

	void sendEmail(String email, String code, String message);

	void resetPassword(String userId, String oldPassword, String password);

	void resetPassword(String userId);

	String randomPassword();

	void deleteUser(Long id);

	String findUserId(String name, String email);

	String login(LoginRequest loginRequest);
}
