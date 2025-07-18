package com.springboot.librarysystem.dto.response;

import com.springboot.librarysystem.constants.BorrowStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BorrowingResponseDto {
	private Long id;
	private Long memberId;
	private Long bookId;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private BorrowStatus status;
}
