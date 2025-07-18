package com.springboot.librarysystem.config;

import com.springboot.librarysystem.entity.UserLog;
import com.springboot.librarysystem.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserActivityLogger {

	private final UserLogRepository userLogRepository;

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
}
