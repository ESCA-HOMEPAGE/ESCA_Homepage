package com.esca.escahp.service;

import com.esca.escahp.dto.UserDto;
import com.esca.escahp.mapper.UserMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements I_UserService {
	@Autowired
	private final UserMapper userMapper;

	@Override
	public List<UserDto> getUserList(){
		return userMapper.getUserList();
	}

}
