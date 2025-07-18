package com.springboot.librarysystem.service.impl;


import com.springboot.librarysystem.constants.BorrowStatus;
import com.springboot.librarysystem.dto.request.BorrowingRequestDto;
import com.springboot.librarysystem.dto.response.BorrowingResponseDto;
import com.springboot.librarysystem.entity.Book;
import com.springboot.librarysystem.entity.Borrowing;
import com.springboot.librarysystem.entity.Member;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.repository.BookRepository;
import com.springboot.librarysystem.repository.BorrowingRepository;
import com.springboot.librarysystem.repository.MemberRepository;
import com.springboot.librarysystem.service.IBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements IBorrowingService {

	private final BorrowingRepository borrowingRepository;
	private final MemberRepository memberRepository;
	private final BookRepository bookRepository;

	@Override
	public BorrowingResponseDto borrowBook(BorrowingRequestDto dto) {
		Member member = memberRepository.findById(dto.getMemberId())
				.orElseThrow(() -> new ResourceNotFoundException("member.not.found"));

		Book book = bookRepository.findById(dto.getBookId())
				.orElseThrow(() -> new ResourceNotFoundException("book.not.found"));

		Borrowing borrowing = Borrowing.builder()
				.member(member)
				.book(book)
				.borrowDate(LocalDate.now())
				.dueDate(dto.getDueDate())
				.status(BorrowStatus.BORROWED)
				.build();

		borrowing = borrowingRepository.save(borrowing);

		return mapToResponse(borrowing);
	}

	@Override
	public BorrowingResponseDto returnBook(Long borrowingId) {
		Borrowing borrowing = borrowingRepository.findById(borrowingId)
				.orElseThrow(() -> new ResourceNotFoundException("borrowing.not.found"));

		borrowing.setReturnDate(LocalDate.now());
		borrowing.setStatus(BorrowStatus.RETURNED);
		borrowing = borrowingRepository.save(borrowing);

		return mapToResponse(borrowing);
	}

	@Override
	public List<BorrowingResponseDto> getAllBorrowings() {
		return borrowingRepository.findAll().stream()
				.map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public BorrowingResponseDto getBorrowingById(Long id) {
		Borrowing borrowing = borrowingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("borrowing.not.found"));

		return mapToResponse(borrowing);
	}

	@Override
	public void deleteBorrowing(Long id) {
		Borrowing borrowing = borrowingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("borrowing.not.found"));

		borrowingRepository.delete(borrowing);
	}

	private BorrowingResponseDto mapToResponse(Borrowing entity) {
		return BorrowingResponseDto.builder()
				.id(entity.getId())
				.memberId(entity.getMember().getId())
				.bookId(entity.getBook().getId())
				.borrowDate(entity.getBorrowDate())
				.dueDate(entity.getDueDate())
				.returnDate(entity.getReturnDate())
				.status(entity.getStatus())
				.build();
	}
}
