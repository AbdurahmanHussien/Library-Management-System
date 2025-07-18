package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.BookDto;
import com.springboot.librarysystem.dto.response.ErrorResponse;
import com.springboot.librarysystem.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get all books",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)))
					,
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)})
public ResponseEntity<List<BookDto>> getAllBooks() {
		List<BookDto> books = bookService.getAllBooks();
		userActivityLogger.logUserAction("Get","All books");
		return ResponseEntity.ok(books);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get book by id")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get book by id",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)))
					,
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)})
	public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
		BookDto book = bookService.getBookById(id);
		userActivityLogger.logUserAction("Get","Book by id: " + id);
		return ResponseEntity.ok(book);
	}

	@PostMapping
	@Operation(summary = "Add book")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Add book",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)))
					,
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)})
	public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
		BookDto createdBook = bookService.addBook(bookDto);
		userActivityLogger.logUserAction("Post","Add book");
		return ResponseEntity.ok(createdBook);
	}

	@PutMapping
	@Operation(summary = "Update book")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Update book",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)))
					,
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)})
	public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto) {
		BookDto updatedBook = bookService.updateBook(bookDto);
		userActivityLogger.logUserAction("Put","Update book with id: " + bookDto.getId());
		return ResponseEntity.ok(updatedBook);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete book")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Delete book",
					content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Book deleted successfully")))
					,
					@ApiResponse(
							responseCode = "400",
							description = "Bad request",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)})
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		userActivityLogger.logUserAction("Delete","Delete book with id: " + id);
		return ResponseEntity.ok("Book deleted successfully");
	}
}
