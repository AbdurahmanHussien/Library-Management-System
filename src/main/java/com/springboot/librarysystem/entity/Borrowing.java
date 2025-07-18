package com.springboot.librarysystem.entity;

import com.springboot.librarysystem.constants.BorrowStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Borrowing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	private Book book;

	private LocalDate borrowDate;

	private LocalDate dueDate;

	private LocalDate returnDate;

	@Enumerated(EnumType.STRING)
	private BorrowStatus status;
}

