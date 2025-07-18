package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.auth.UserDto;
import java.util.List;
import java.util.Optional;

public interface IUserService {

	Optional<UserDto> getUserById(Long id);

	UserDto createUser(UserDto userDto, List<Long> roleIds);

	List<UserDto> getAllUsers();

	UserDto updateUser(UserDto userDto,  List<Long> roleIds);

	void deleteUser(Long id);

}
