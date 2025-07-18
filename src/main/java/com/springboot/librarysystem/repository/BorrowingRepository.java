package com.springboot.librarysystem.repository;

import com.springboot.librarysystem.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

}

