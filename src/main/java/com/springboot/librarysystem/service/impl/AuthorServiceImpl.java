package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.AuthorDto;
import com.springboot.librarysystem.entity.Author;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.AuthorMapper;
import com.springboot.librarysystem.repository.AuthorRepository;
import com.springboot.librarysystem.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

	private final AuthorRepository authorRepository;

	@Override
	public AuthorDto addAuthor(AuthorDto dto) {
		if(Objects.nonNull(dto.getId())) {
			throw new RuntimeException("Id must be null");
		}

		Author author = AuthorMapper.INSTANCE.toEntity(dto);
		Author savedAuthor = authorRepository.save(author);
		return AuthorMapper.INSTANCE.toDto(savedAuthor);
	}

	@Override
	public List<AuthorDto> getAllAuthors() {
		return authorRepository.findAll()
				.stream()
				.map(AuthorMapper.INSTANCE::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public AuthorDto getAuthorById(Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with"));
		return AuthorMapper.INSTANCE.toDto(author);
	}

	@Override
	public AuthorDto updateAuthor(AuthorDto authorDto) {
		Author existing = authorRepository.findById(authorDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Author not found"));

		existing.setName(authorDto.getName());
		existing.setBio(authorDto.getBio());

		Author updated = authorRepository.save(existing);
		return AuthorMapper.INSTANCE.toDto(updated);
	}

	@Override
	public void deleteAuthor(Long id) {
		if (!authorRepository.existsById(id)) {
			throw new ResourceNotFoundException("Author not found with id: " + id);
		}
		authorRepository.deleteById(id);
	}
}
