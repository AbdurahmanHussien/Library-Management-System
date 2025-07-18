package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.BookDto;
import java.util.List;

public interface IBookService {

	List<BookDto> getAllBooks();

	BookDto getBookById(Long id);

	BookDto addBook(BookDto bookDto);

	BookDto updateBook(BookDto bookDto);

	void deleteBookById(Long id);



}
