package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.request.BorrowingRequestDto;
import com.springboot.librarysystem.dto.response.BorrowingResponseDto;
import java.util.List;

public interface IBorrowingService {

	BorrowingResponseDto borrowBook(BorrowingRequestDto requestDto);
	BorrowingResponseDto returnBook(Long borrowingId);
	List<BorrowingResponseDto> getAllBorrowings();
	BorrowingResponseDto getBorrowingById(Long id);
	void deleteBorrowing(Long id);
}
