package com.springboot.librarysystem.mapper;


import com.springboot.librarysystem.dto.UserLogDto;
import com.springboot.librarysystem.entity.UserLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserLogMapper {

	UserLogMapper INSTANCE = Mappers.getMapper(UserLogMapper.class);


	List<UserLogDto> toDtoList( List<UserLog> userLogs);

	List<UserLog> toEntityList(List<UserLogDto> userLogDto);


}
