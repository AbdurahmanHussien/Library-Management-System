package com.springboot.librarysystem.service.impl;


import com.springboot.librarysystem.dto.LanguageDto;
import com.springboot.librarysystem.entity.Language;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.LanguageMapper;
import com.springboot.librarysystem.repository.LanguageRepository;
import com.springboot.librarysystem.service.ILanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements ILanguageService {


	private final LanguageRepository languageRepository;


	@Override
	public LanguageDto addLanguage(LanguageDto dto) {
		if(Objects.nonNull(dto.getId())) {
			throw new BadRequestException("Id must be null");
		}
		Language language = LanguageMapper.INSTANCE.toEntity(dto);
		Language saved = languageRepository.save(language);
		return LanguageMapper.INSTANCE.toDto(saved);
	}

	@Override
	public List<LanguageDto> getAllLanguages() {
		return languageRepository.findAll()
				.stream()
				.map(LanguageMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public LanguageDto getLanguageById(Long id) {
		Language language = languageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Language not found"));
		return LanguageMapper.INSTANCE.toDto(language);
	}

	@Override
	public LanguageDto updateLanguage(LanguageDto dto) {
		if (Objects.isNull(dto.getId())) {
			throw new BadRequestException("Id cannot be null");
		}
		Language existing = languageRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Language not found"));

		existing.setName(dto.getName());

		Language updated = languageRepository.save(existing);
		return LanguageMapper.INSTANCE.toDto(updated);
	}

	@Override
	public void deleteLanguage(Long id) {
		Language language = languageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Language not found"));
		languageRepository.delete(language);
	}
}
