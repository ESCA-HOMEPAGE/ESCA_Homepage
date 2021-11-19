package com.esca.escahp.mapper;

import com.esca.escahp.dto.UserDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
	List<UserDto> getUserList();
}
