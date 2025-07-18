package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.AuthorDto;
import com.springboot.librarysystem.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

	AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

	AuthorDto toDto(Author author);

	Author toEntity(AuthorDto dto);
}
