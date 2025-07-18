package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.LanguageDto;
import com.springboot.librarysystem.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface LanguageMapper {

	LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

	LanguageDto toDto(Language language);
	Language toEntity(LanguageDto dto);
}
