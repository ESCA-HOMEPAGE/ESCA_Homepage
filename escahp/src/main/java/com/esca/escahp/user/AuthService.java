package com.esca.escahp.user;

import com.esca.escahp.user.dto.AuthResponse;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements I_AuthService{

	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public AuthResponse isCheckUser(String userId) {
		User auth = userRepository.findByUserIdAndRank(userId, 4);
		return null;
	}

	@Override
	public void addUser(User auth) {

	}

	@Override
	public void validateUser(String userId) {

	}

	@Override
	public void resetPassword(String userId, String password) {

	}

	@Override
	public boolean checkOldPassword(String userId) {
		return true;
	}

	@Override
	public void deleteUser(String userId) {

	}

	@Override
	public String findUserId(String email) {
		return null;
	}
}
