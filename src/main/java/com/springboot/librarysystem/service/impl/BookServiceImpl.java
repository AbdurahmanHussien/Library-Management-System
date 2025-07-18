package com.springboot.librarysystem.service.impl;

import com.springboot.librarysystem.dto.BookDto;
import com.springboot.librarysystem.entity.Author;
import com.springboot.librarysystem.entity.Book;
import com.springboot.librarysystem.entity.Category;
import com.springboot.librarysystem.entity.Language;
import com.springboot.librarysystem.exception.BadRequestException;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.repository.*;
import com.springboot.librarysystem.service.IBookService;
import jakarta.transaction.Transactional;
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
		List<BookDto> books = bookRepository.findAll()
				.stream()
				.map(this::convertToDto)
				.toList();
		if (books.isEmpty()) {
			throw new ResourceNotFoundException("no.books.found");
		}
		return books;
	}

	@Override
	public BookDto getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("book.not.found"));
		return convertToDto(book);
	}

	@Override
	@Transactional
	public BookDto addBook(BookDto bookDto) {
		if (bookDto.getId() != null) {
			throw new BadRequestException("id.must.be.null");
		}
		Book book = convertToEntity(bookDto);
		Book savedBook = bookRepository.save(book);
		return convertToDto(savedBook);
	}

	@Override
	@Transactional
	public BookDto updateBook(BookDto bookDto) {
		if (bookDto.getId() == null) {
			throw new BadRequestException("id.required");
		}

		getBookById(bookDto.getId());

		Book book = convertToEntity(bookDto);
		Book updatedBook = bookRepository.save(book);
		return convertToDto(updatedBook);
	}

	@Override
	public void deleteBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("book.not.found"));
		bookRepository.delete(book);
	}

	private BookDto convertToDto(Book book) {
		if (book.getLanguage() == null) {
			throw new ResourceNotFoundException("language.not.found");
		}
		List<Author> authors = book.getAuthors();
		if (authors.isEmpty()) {
			throw new ResourceNotFoundException("author.not.found");
		}
		List<Category> categories = book.getCategories();
		if (categories.isEmpty()) {
			throw new ResourceNotFoundException("category.not.found");
		}
		
		return BookDto.builder()
			.id(book.getId())
			.title(book.getTitle())
			.isbn(book.getIsbn())
			.edition(book.getEdition())
			.publicationYear(book.getPublicationYear())
			.summary(book.getSummary())
			.coverImageUrl(book.getCoverImageUrl())
			.languageId(book.getLanguage().getId())
			.publisherId(book.getPublisher() != null ? book.getPublisher().getId() : null)
			.authorIds(book.getAuthors().stream().map(Author::getId).toList())
			.categoryIds(book.getCategories().stream().map(Category::getId).toList())
			.build();
	}

	private Book convertToEntity(BookDto bookDto) {
		return Book.builder()
			.id(bookDto.getId())
			.title(bookDto.getTitle())
			.isbn(bookDto.getIsbn())
			.edition(bookDto.getEdition())
			.publicationYear(bookDto.getPublicationYear())
			.summary(bookDto.getSummary())
			.coverImageUrl(bookDto.getCoverImageUrl())
			.language(languageRepository.findById(bookDto.getLanguageId())
					.orElseThrow(() -> new ResourceNotFoundException("language.not.found")))
			.publisher(publisherRepository.findById(bookDto.getPublisherId())
					.orElseThrow(() -> new ResourceNotFoundException("publisher.not.found")))
			.authors(authorRepository.findAllById(bookDto.getAuthorIds()))
			.categories(categoryRepository.findAllById(bookDto.getCategoryIds()))
			.build();
	}

}
