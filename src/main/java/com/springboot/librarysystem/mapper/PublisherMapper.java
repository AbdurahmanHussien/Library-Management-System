package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.PublisherDto;
import com.springboot.librarysystem.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

	PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);


	PublisherDto toDto(Publisher publisher);


	Publisher toEntity(PublisherDto dto);

}
