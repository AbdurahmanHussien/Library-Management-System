package com.springboot.librarysystem.dataInitializer;

import com.springboot.librarysystem.constants.Languages;
import com.springboot.librarysystem.entity.Language;
import com.springboot.librarysystem.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageInitializer implements CommandLineRunner {

	private final LanguageRepository languageRepository;

	@Override
	public void run(String... args) {
		for (Languages lang : Languages.values()) {
			if (!languageRepository.existsLanguageByName(lang)) {
				languageRepository.save(new Language(lang));
			}
		}
	}
}
