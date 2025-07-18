package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.LanguageDto;
import java.util.List;

public interface ILanguageService {
	LanguageDto addLanguage(LanguageDto dto);
	List<LanguageDto> getAllLanguages();
	LanguageDto getLanguageById(Long id);
	LanguageDto updateLanguage(LanguageDto dto);
	void deleteLanguage(Long id);

}
