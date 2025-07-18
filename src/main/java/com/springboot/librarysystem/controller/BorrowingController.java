package com.springboot.librarysystem.controller;

import com.springboot.librarysystem.config.UserActivityLogger;
import com.springboot.librarysystem.dto.request.BorrowingRequestDto;
import com.springboot.librarysystem.dto.response.BorrowingResponseDto;
import com.springboot.librarysystem.dto.response.ErrorResponse;
import com.springboot.librarysystem.service.IBorrowingService;
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
@RequestMapping("/borrowing")
@RequiredArgsConstructor
public class BorrowingController {

	private final IBorrowingService borrowingService;
	private final UserActivityLogger userActivityLogger;

	@PostMapping
	@Operation(summary = "Borrow book")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Borrow book",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowingResponseDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)}
	)
	public ResponseEntity<BorrowingResponseDto> borrowBook(@Valid @RequestBody BorrowingRequestDto dto) {
		BorrowingResponseDto responseDto = borrowingService.borrowBook(dto);
		userActivityLogger.logUserAction("Post","Member with id: " + dto.getMemberId() + " Borrowed book with id: " + dto.getBookId());
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/return/{id}")
	@Operation(summary = "Return book")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Return book",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowingResponseDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)}
	)
	public ResponseEntity<BorrowingResponseDto> returnBook(@PathVariable Long id) {
		BorrowingResponseDto responseDto = borrowingService.returnBook(id);
		userActivityLogger.logUserAction("Put","Member with id: " + responseDto.getMemberId() + " Returned book with id: " + responseDto.getBookId());
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	@Operation(summary = "Get all borrowings")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get all borrowings",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowingResponseDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)}
	)
	public ResponseEntity<List<BorrowingResponseDto>> getAllBorrowings() {
		return ResponseEntity.ok(borrowingService.getAllBorrowings());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get borrowing by id")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Get borrowing by id",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = BorrowingResponseDto.class))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)}
	)
	public ResponseEntity<BorrowingResponseDto> getBorrowingById(@PathVariable Long id) {
		return ResponseEntity.ok(borrowingService.getBorrowingById(id));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete borrowing")
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "Delete borrowing",
					content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "Borrowing deleted successfully"))
			),
			@ApiResponse(
					responseCode = "400",
					description = "Bad request",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
			)}
	)
	public ResponseEntity<?> deleteBorrowing(@PathVariable Long id) {
		borrowingService.deleteBorrowing(id);
		userActivityLogger.logUserAction("Delete","Deleted Borrowing with id: " + id);
		return ResponseEntity.ok("Borrowing deleted successfully");
	}
}
