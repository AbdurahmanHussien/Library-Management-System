package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.auth.UserDto;
import com.springboot.librarysystem.entity.auth.Role;
import com.springboot.librarysystem.entity.auth.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "roleIds", expression = "java(getRoleIds(user.getRoles()))")
	UserDto toDto(User user);

	@Mapping(target = "roles", ignore = true)
	@Mapping(target = "createdAt", ignore = true)

	User toEntity(UserDto dto);

	default Set<Long> getRoleIds(Set<Role> roles) {
		if (roles == null) return new HashSet<>();
		return roles.stream().map(Role::getId).collect(Collectors.toSet());
	}
}
