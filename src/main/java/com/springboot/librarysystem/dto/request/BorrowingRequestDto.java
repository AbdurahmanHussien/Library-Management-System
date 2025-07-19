package com.springboot.librarysystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Data
@Builder
public class BorrowingRequestDto {

	@NotNull(message = "Member ID is required")
	@Schema(description = "Member ID", example = "1")
	public Long memberId;

	@NotNull(message = "Book ID is required")
	@Schema(description = "Book ID", example = "1")
	public Long bookId;

	@NotNull(message = "Borrow date is required")
	@Schema(description = "Borrow date", example = "2023-05-01")
	public LocalDate borrowDate;

}
