package com.springboot.librarysystem.mapper;


import com.springboot.librarysystem.dto.UserLogDto;
import com.springboot.librarysystem.entity.UserLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserLogMapper {

	UserLogMapper INSTANCE = Mappers.getMapper(UserLogMapper.class);


	UserLogDto toDtoList(UserLog userLog);

	UserLog toEntityList(UserLogDto userLogDto);

	UserLogDto toDto(UserLog userLog);

	UserLog toEntity(UserLogDto userLogDto);
}
