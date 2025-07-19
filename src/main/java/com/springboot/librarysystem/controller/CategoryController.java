package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.service.UserLogService;
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
	private final UserLogService userLogService;

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
		userLogService.logUserAction("create", "New Category with name : "+ dto.getName());
		return ResponseEntity.ok(categoryDto);
	}

	@PutMapping
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto) {
		CategoryDto categoryDto = categoryService.updateCategory(dto);
		userLogService.logUserAction("update", "Updated Category with id : "+ dto.getId());
		return ResponseEntity.ok(categoryDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		userLogService.logUserAction("delete", "Deleted Category with id : "+ id);
		return ResponseEntity.ok("category deleted successfully");
	}}
