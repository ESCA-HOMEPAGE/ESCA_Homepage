package com.esca.escahp.user.config;

import com.esca.escahp.exception.EscaException;
import com.esca.escahp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userJpaRepo;

	public UserDetails loadUserByUsername(String userPk) {
		return userJpaRepo.findByUserId(userPk);
	}
}
