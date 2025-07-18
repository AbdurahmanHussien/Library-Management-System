package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.auth.UserDto;
import com.springboot.librarysystem.entity.auth.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


	@Mapping(target = "roleIds", source = "roles")
	UserDto toDto(User user);

	@Mapping(target = "roles", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	User toEntity(UserDto dto);
}
