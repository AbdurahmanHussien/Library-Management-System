package com.springboot.librarysystem.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserLogDto {

	private Long id;
	private String username;
	private String ipAddress;
	private String actionType;
	private String details;
	private LocalDateTime time;


}
