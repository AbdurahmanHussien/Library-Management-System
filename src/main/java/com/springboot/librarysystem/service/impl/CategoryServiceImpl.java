package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.CategoryDto;
import com.springboot.librarysystem.entity.Category;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.CategoryMapper;
import com.springboot.librarysystem.repository.CategoryRepository;
import com.springboot.librarysystem.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

	private final CategoryRepository categoryRepository;



	@Override
	@CacheEvict(value = {"categories", "category"}, allEntries = true)
	public CategoryDto createCategory(CategoryDto dto) {
		Category parent = null;
		if (dto.getParentId() != null) {
			parent = categoryRepository.findById(dto.getParentId())
					.orElseThrow(() -> new ResourceNotFoundException("parent.category.not.found"));
		}

		Category category = CategoryMapper.INSTANCE.toEntity(dto);
		category.setParent(parent);

		return CategoryMapper.INSTANCE.toDto(categoryRepository.save(category));
	}

	@Override
	@Cacheable(value = "categories")
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return CategoryMapper.INSTANCE.toDtoList(categories);
	}

	@Override
	@Cacheable(value = "category", key = "#id")
	public CategoryDto getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category.not.found"));
		return CategoryMapper.INSTANCE.toDto(category);
	}

	@Override
	@CacheEvict(value = {"categories", "category"}, allEntries = true)
	public CategoryDto updateCategory( CategoryDto dto) {

		Category existing = categoryRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("category.not.found"));

		existing.setName(dto.getName());

		if (dto.getParentId() != null) {
			Category parent = categoryRepository.findById(dto.getParentId())
					.orElseThrow(() -> new ResourceNotFoundException("parent.category.not.found"));
			existing.setParent(parent);
		} else {
			existing.setParent(null);
		}

		return CategoryMapper.INSTANCE.toDto(categoryRepository.save(existing));
	}

	@Override
	@CacheEvict(value = {"categories", "category"}, allEntries = true)
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category.not.found"));
		categoryRepository.delete(category);
	}
}
