package com.springboot.librarysystem.service.impl;


import com.springboot.librarysystem.dto.auth.UserDto;
import com.springboot.librarysystem.entity.auth.Role;
import com.springboot.librarysystem.entity.auth.User;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.DuplicateFieldException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.UserMapper;
import com.springboot.librarysystem.repository.RoleRepository;
import com.springboot.librarysystem.repository.UserRepository;
import com.springboot.librarysystem.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Override
	@Cacheable(value = "user", key = "#id")
	public Optional<UserDto> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("user.not.found");
		}
		return user.map(UserMapper.INSTANCE::toDto);
	}

	@Override
	@CacheEvict(value = {"user", "users"}, allEntries = true)
	public UserDto createUser(UserDto userDto, List<Long> roleIds) {
		if (Objects.nonNull(userDto.getId())) {
			throw new BadRequestException("id.must.be.null");
		}
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new DuplicateFieldException("user.exists");
		}
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new DuplicateFieldException("choose.another.username");
		}
		return saveUser(userDto, roleIds);
	}

	@Override
	@Cacheable(value = "users")
	public List<UserDto> getAllUsers() {

		List<UserDto> users = userRepository.findAll()
				.stream()
				.map(UserMapper.INSTANCE::toDto)
				.toList();
		if (users.isEmpty()) {
			throw new ResourceNotFoundException("no.users.found");
		}
		return users;

	}

	@Override
	@CacheEvict(value = {"user", "users"}, allEntries = true)
	public UserDto updateUser(UserDto userDto, List<Long> roleIds) {
		if (Objects.isNull(userDto.getId())) {
			throw new BadRequestException("id.required");
		}

		User existingUser = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("user.not.found"));

		existingUser.setUsername(userDto.getUsername());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setActive(userDto.isActive());

		if (Objects.nonNull(userDto.getPassword())) {
			existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}

		existingUser.setRoles(new HashSet<>(roleRepository.findAllById(roleIds)));

		return UserMapper.INSTANCE.toDto(userRepository.save(existingUser));
	}

	@Override
	@CacheEvict(value = {"user", "users"}, allEntries = true)
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
		userRepository.delete(user);
	}


	private UserDto saveUser(UserDto userDto, List<Long> roleIds) {
		List<Role> roles = roleRepository.findAllById(roleIds);

		if (roles.size() != roleIds.size()) {
			throw new ResourceNotFoundException("role.not.found");
		}

		User user = User.builder()
				.username(userDto.getUsername())
				.email(userDto.getEmail())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.roles(new HashSet<>(roles))
				.isActive(true)
				.build();

		User savedUser = userRepository.save(user);
		return UserMapper.INSTANCE.toDto(savedUser);
	}

}
