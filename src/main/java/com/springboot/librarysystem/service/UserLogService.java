package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.UserLogDto;
import com.springboot.librarysystem.entity.UserLog;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.mapper.UserLogMapper;
import com.springboot.librarysystem.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLogService {

	private final UserLogRepository userLogRepository;


	@CacheEvict(value = {"findByUsername", "getAllUserLogs"}, allEntries = true)
	public void logUserAction(String actionType, String details) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (auth != null) ? auth.getName() : "Anonymous";

		String ip = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest().getRemoteAddr();

		UserLog log = UserLog.builder()
				.username(username)
				.actionType(actionType)
				.details(details)
				.ipAddress(ip)
				.time(LocalDateTime.now())
				.build();

		userLogRepository.save(log);
	}


	@Cacheable(value = "findByUsername", key = "#username")
	public List<UserLogDto> findByUsername(String username) {
		if (userLogRepository.findByUsername(username) == null) {
			throw new ResourceNotFoundException("user.not.found");
		}

			List<UserLog> userLogs =userLogRepository.findByUsername(username);

		return UserLogMapper.INSTANCE.toDtoList(userLogs);
	}

	@Cacheable(value = "getAllUserLogs")
	public List<UserLogDto> getAllUserLogs() {
		List<UserLog> userLogs = userLogRepository.findAll();
		return UserLogMapper.INSTANCE.toDtoList(userLogs);
	}
}

