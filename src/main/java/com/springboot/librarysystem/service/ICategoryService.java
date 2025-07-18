package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.CategoryDto;
import java.util.List;

public interface ICategoryService {
	CategoryDto createCategory(CategoryDto dto);

	List<CategoryDto> getAllCategories();

	CategoryDto getCategoryById(Long id);

	CategoryDto updateCategory( CategoryDto dto);

	void deleteCategory(Long id);
}
