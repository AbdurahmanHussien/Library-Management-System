package com.springboot.librarysystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.librarysystem.constants.BorrowStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowingResponseDto {
	@Schema(example = "1")
	private Long id;
	@Schema(example = "1")
	private Long memberId;
	@Schema(example = "1")
	private Long bookId;
	@Schema(example = "2023-01-01")
	private LocalDate borrowDate;
	@Schema(example = "2023-01-01")
	private LocalDate dueDate;
	@Schema(example = "2023-01-01")
	private LocalDate returnDate;
	@Schema(example = "BORROWED")
	private BorrowStatus status;
}
