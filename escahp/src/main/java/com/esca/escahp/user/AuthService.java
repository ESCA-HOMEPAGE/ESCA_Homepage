package com.esca.escahp.user;

import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.dto.AuthResponse;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.exception.ResourceNotFoundException;
import com.esca.escahp.user.exception.SignUpException;
import com.esca.escahp.user.repository.UserRepository;
import java.util.Random;
import org.apache.ibatis.ognl.BooleanExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements I_AuthService{

	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User checkUserId(String userId) {
		User user = userRepository.findByUserId(userId);

		return user == null ? null : user;
	}

	@Transactional
	@Override
	public void addUser(User auth) {
		// 1. 아이디 중복 체크
		User user = checkUserId(auth.getUserId());
		if(user.getRank() != 4) throw new SignUpException("이미 회원가입된 회원입니다.");

		// 2. 닉네임 체크
		if(userRepository.findByNicknameAndGeneration(
			auth.getNickname(),
			auth.getGeneration()
		) != null) throw new SignUpException("중복된 닉네임입니다.");

		// 3. 회원가입
		if(user != null)
			auth.setId(user.getId());
		userRepository.save(auth);

		// 4. 회원인증 메일 전송
		sendEmail(auth.getEmail(), UserCode.VALIDATE.name(), "");

	}

	@Override
	public void sendEmail(String email, String code, String message) {

	}

	@Transactional
	@Override
	public void resetPassword(String userId, String oldPassword, String newPassword) {
		User user = userRepository.findByUserId(userId);
		if(user == null || user.getRank() == 4) throw new ResourceNotFoundException(userId, "아이디가 다릅니다.");
		if(user.getPassword() != oldPassword) throw new SignUpException("비밀번호가 다릅니다.");

		userRepository.updatePassword(newPassword, user.getId());
	}

	@Transactional
	@Override
	public void resetPassword(String userId){
		// 1. 회원 정보
		User user = userRepository.findByUserId(userId);

		// 2. 비밀번호 초기화
		String newPassword = randomPassword();
		userRepository.updatePassword(newPassword, user.getId());

		// 3. 비밀번호 메일로 전송
		sendEmail(user.getEmail(), UserCode.RESET_PASSWORD.name(), newPassword);

	}

	@Override
	public String randomPassword() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetLength = 10;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1)
			.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			.limit(targetLength)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
	}

	@Transactional
	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(id.toString(), "없는 회원입니다."));

		userRepository.delete(user);
	}

	@Override
	public String findUserId(String name, String email) {
		User user = userRepository.findByNameAndEmail(name, email);
		if(user == null) throw new ResourceNotFoundException(name, "일치하는 회원이 없습니다.");

		return user.getUserId();
	}
}
