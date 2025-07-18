package com.springboot.librarysystem.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Data
@Builder
public class BorrowingRequestDto {

	@NotNull(message = "Member ID is required")
	public Long memberId;

	@NotNull(message = "Book ID is required")
	public Long bookId;

	@NotNull(message = "Borrow date is required")
	public LocalDate borrowDate;

	@FutureOrPresent(message = "Due date must be today or in the future")
	public LocalDate dueDate;
}
