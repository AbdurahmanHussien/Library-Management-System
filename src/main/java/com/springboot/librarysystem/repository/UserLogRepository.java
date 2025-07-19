package com.springboot.librarysystem.repository;

import com.springboot.librarysystem.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

	List<UserLog> findByUsername(String username);
}
