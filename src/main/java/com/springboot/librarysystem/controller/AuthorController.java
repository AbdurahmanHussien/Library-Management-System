package com.springboot.librarysystem.controller;


import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.AuthorDto;
import com.springboot.librarysystem.dto.auth.UserDto;
import com.springboot.librarysystem.dto.response.ErrorResponse;
import com.springboot.librarysystem.service.IAuthorService;
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
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

	private final IAuthorService authorService;
	private final UserActivityLogger userActivityLogger;



	@GetMapping
	@Operation(summary = "Get all authors")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get all authors",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
),
		@ApiResponse(
				responseCode = "400",
				description = "Bad request",
				content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
		)

	})
	public ResponseEntity<List<AuthorDto>> getAllAuthors() {
		List<AuthorDto> authors = authorService.getAllAuthors();
		userActivityLogger.logUserAction("Get","All authors");
		return ResponseEntity.ok().body(authors);

	}


	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get  author by id",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)

	})
	@GetMapping("/{id}")
	@Operation(summary = "Get author by id")
	public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
		AuthorDto author = authorService.getAuthorById(id);
		userActivityLogger.logUserAction("Get","Author by id: " + id);
		return ResponseEntity.ok().body(author);
	}



	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "create author",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)

	})
	@PostMapping
	@Operation(summary = "Create author")
	public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
		AuthorDto author = authorService.addAuthor(authorDto);
		userActivityLogger.logUserAction("Post", "Create author");
		return ResponseEntity.ok().body(author);
	}



	@PutMapping
	@Operation(summary = "Update author")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "update author",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)

	})
	public ResponseEntity<AuthorDto> updateAuthor(@Valid @RequestBody AuthorDto authorDto) {
		AuthorDto author = authorService.updateAuthor(authorDto);
		userActivityLogger.logUserAction("Put", "Update author with id: " + authorDto.getId());
		return ResponseEntity.ok().body(author);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete author")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "delete author",
					content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Author deleted successfully"))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)

	})
	public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
		authorService.deleteAuthor(id);
		userActivityLogger.logUserAction("Delete", "Delete author with id: " + id);
		return ResponseEntity.ok("Author deleted successfully");
	}

}
