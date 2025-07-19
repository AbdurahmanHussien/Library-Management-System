package com.springboot.librarysystem.controller;


import com.springboot.librarysystem.dto.UserLogDto;
import com.springboot.librarysystem.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userLog")
@RequiredArgsConstructor
public class UserLogController {

	private final UserLogService userLogService;

	@RequestMapping("{username}")
	public ResponseEntity<List<UserLogDto>> getLogsByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userLogService.findByUsername(username));
	}

	@RequestMapping
	public ResponseEntity<List<UserLogDto>> getAllLogs() {
		return ResponseEntity.ok(userLogService.getAllUserLogs());
	}
}
