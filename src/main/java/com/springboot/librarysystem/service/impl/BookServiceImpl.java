package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.BookDto;
import com.springboot.librarysystem.entity.Author;
import com.springboot.librarysystem.entity.Book;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.repository.*;
import com.springboot.librarysystem.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final CategoryRepository categoryRepository;
	private final PublisherRepository publisherRepository;
	private final LanguageRepository languageRepository;

	@Override
	public List<BookDto> getAllBooks() {
		return bookRepository.findAll()
				.stream()
				.map(this::convertToDto)
				.toList();
	}

	@Override
	public BookDto getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		return convertToDto(book);
	}

	@Override
	public BookDto addBook(BookDto bookDto) {
		if (bookDto.getId() != null) {
			throw new BadRequestException("New book cannot have id");
		}
		Book book = convertToEntity(bookDto);
		Book savedBook = bookRepository.save(book);
		return convertToDto(savedBook);
	}

	@Override
	public BookDto updateBook(BookDto bookDto) {
		if (bookDto.getId() == null) {
			throw new BadRequestException("Book id cannot be null");
		}

		getBookById(bookDto.getId());

		Book book = convertToEntity(bookDto);
		Book updatedBook = bookRepository.save(book);
		return convertToDto(updatedBook);
	}

	@Override
	public void deleteBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		bookRepository.delete(book);
	}


	private BookDto convertToDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setEdition(book.getEdition());
		bookDto.setPublicationYear(book.getPublicationYear());
		bookDto.setSummary(book.getSummary());
		bookDto.setCoverImageUrl(book.getCoverImageUrl());
		bookDto.setLanguageId(book.getLanguage().getId());
		bookDto.setPublisherId(book.getPublisher().getId());
		bookDto.setAuthorIds(book.getAuthors().stream().map(Author::getId).toList());
		return bookDto;
	}

	private Book convertToEntity(BookDto bookDto) {
		Book book = new Book();
		book.setId(bookDto.getId());
		book.setTitle(bookDto.getTitle());
		book.setIsbn(bookDto.getIsbn());
		book.setEdition(bookDto.getEdition());
		book.setPublicationYear(bookDto.getPublicationYear());
		book.setSummary(bookDto.getSummary());
		book.setCoverImageUrl(bookDto.getCoverImageUrl());
		book.setLanguage(languageRepository.findById(bookDto.getLanguageId()).orElse(null));
		book.setPublisher(publisherRepository.findById(bookDto.getPublisherId()).orElse(null));
		book.setAuthors(authorRepository.findAllById(bookDto.getAuthorIds()));
		return book;
	}
}
