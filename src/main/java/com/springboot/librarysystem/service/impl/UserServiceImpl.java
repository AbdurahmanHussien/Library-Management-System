package com.springboot.librarysystem.service.impl;


import com.springboot.librarysystem.dto.auth.UserDto;
import com.springboot.librarysystem.entity.auth.Role;
import com.springboot.librarysystem.entity.auth.User;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.UserMapper;
import com.springboot.librarysystem.repository.RoleRepository;
import com.springboot.librarysystem.repository.UserRepository;
import com.springboot.librarysystem.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Override
	public Optional<UserDto> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("User not found");
		}
		return user.map(UserMapper.INSTANCE::toDto);
	}

	@Override
	public UserDto createUser(UserDto userDto, List<Long> roleIds) {
		if (Objects.nonNull(userDto.getId())) {
			throw new RuntimeException("Id must be null");
		}
		return saveUser(userDto, roleIds);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto userDto, List<Long> roleIds) {
		if (Objects.isNull(userDto.getId())) {
			throw new RuntimeException("Id cannot be null");
		}
		getUserById(userDto.getId());

		return saveUser(userDto, roleIds);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
	}

	private UserDto saveUser(UserDto userDto, List<Long> roleIds) {
		List<Role> roles = roleRepository.findAllById(roleIds);

		if (roles.size() != roleIds.size()) {
			throw new ResourceNotFoundException("Some roles not found");
		}

		User user = User.builder()
				.username(userDto.getUsername())
				.email(userDto.getEmail())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.roles(new HashSet<>(roles))
				.isActive(true)
				.createdAt(LocalDateTime.now())
				.build();

		User savedUser = userRepository.save(user);
		return UserMapper.INSTANCE.toDto(savedUser);
	}
}
