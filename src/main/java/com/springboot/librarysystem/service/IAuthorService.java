package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.AuthorDto;
import java.util.List;

public interface IAuthorService {

	AuthorDto addAuthor(AuthorDto author);
	List<AuthorDto> getAllAuthors();
	AuthorDto getAuthorById(Long id);
	AuthorDto updateAuthor(AuthorDto author);
	void deleteAuthor(Long id);
}
