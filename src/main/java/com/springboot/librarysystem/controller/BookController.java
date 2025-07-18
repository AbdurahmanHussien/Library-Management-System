package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.BookDto;
import com.springboot.librarysystem.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

	private final IBookService bookService;
	private final UserActivityLogger userActivityLogger;

	@GetMapping
	@Operation(summary = "Get all books")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		List<BookDto> books = bookService.getAllBooks();
		userActivityLogger.logUserAction("Get","All books");
		return ResponseEntity.ok(books);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get book by id")
	public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
		BookDto book = bookService.getBookById(id);
		userActivityLogger.logUserAction("Get","Book by id: " + id);
		return ResponseEntity.ok(book);
	}

	@PostMapping
	@Operation(summary = "Add book")
	public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
		BookDto createdBook = bookService.addBook(bookDto);
		userActivityLogger.logUserAction("Post","Add book");
		return ResponseEntity.ok(createdBook);
	}

	@PutMapping
	@Operation(summary = "Update book")
	public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto) {
		BookDto updatedBook = bookService.updateBook(bookDto);
		userActivityLogger.logUserAction("Put","Update book with id: " + bookDto.getId());
		return ResponseEntity.ok(updatedBook);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete book")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		userActivityLogger.logUserAction("Delete","Delete book with id: " + id);
		return ResponseEntity.ok("Book deleted successfully");
	}
}
