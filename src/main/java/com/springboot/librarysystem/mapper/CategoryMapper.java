package com.springboot.librarysystem.mapper;

import com.springboot.librarysystem.dto.CategoryDto;
import com.springboot.librarysystem.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(target = "parent", ignore = true)
	Category toEntity(CategoryDto dto);
	@Mapping(source = "parent.id", target = "parentId")
	CategoryDto toDto(Category category);

	List<CategoryDto> toDtoList(List<Category> categories);

}