package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.CategoryDto;
import com.springboot.librarysystem.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

	private final ICategoryService categoryService;
	private final UserActivityLogger userActivityLogger;

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto dto) {
		CategoryDto categoryDto = categoryService.createCategory(dto);
		userActivityLogger.logUserAction("create", "New Category");
		return ResponseEntity.ok(categoryDto);
	}

	@PutMapping
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto) {
		CategoryDto categoryDto = categoryService.updateCategory(dto);
		userActivityLogger.logUserAction("update", "Updated Category with id : "+ dto.getId());
		return ResponseEntity.ok(categoryDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		userActivityLogger.logUserAction("delete", "Deleted Category with id : "+ id);
		return ResponseEntity.ok("category deleted successfully");
	}}
