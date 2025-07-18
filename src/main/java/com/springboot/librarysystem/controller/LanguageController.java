package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.LanguageDto;
import com.springboot.librarysystem.service.ILanguageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {

	private final ILanguageService languageService;
	private final UserActivityLogger userActivityLogger;


	@GetMapping
	@Operation(summary = "Get all languages")
	public ResponseEntity<?> getAllLanguages() {
		List<LanguageDto> languages = languageService.getAllLanguages();
		userActivityLogger.logUserAction("Get","All languages");
		return ResponseEntity.ok(languages);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get language by id")
	public ResponseEntity<LanguageDto> getLanguageById(@PathVariable Long id) {
		LanguageDto language = languageService.getLanguageById(id);
		userActivityLogger.logUserAction("Get","Language by id: " + id);
		return ResponseEntity.ok(language);
	}

	@PostMapping
	public ResponseEntity<LanguageDto> addLanguage(@Valid @RequestBody LanguageDto dto) {
		LanguageDto language = languageService.addLanguage(dto);
		userActivityLogger.logUserAction("Post","Add language: " + dto.getName());
		return ResponseEntity.ok(language);
	}

	@PutMapping
	public ResponseEntity<LanguageDto> updateLanguage(@Valid @RequestBody LanguageDto dto) {
		LanguageDto language = languageService.updateLanguage(dto);
		userActivityLogger.logUserAction("Put","Update language: " + dto.getName());
		return ResponseEntity.ok(language);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLanguage(@PathVariable Long id) {
		 languageService.deleteLanguage(id);
		userActivityLogger.logUserAction("Delete","Delete language with id: " + id);
		return ResponseEntity.ok("language deleted successfully");
	}
}
