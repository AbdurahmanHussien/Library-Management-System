package com.springboot.librarysystem.config;

import com.springboot.librarysystem.entity.UserLog;
import com.springboot.librarysystem.repository.UserLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserActivityLogger extends OncePerRequestFilter {

	private final UserLogRepository userLogRepository;
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
								throws ServletException, IOException {

		String endpoint = request.getRequestURI();
		String method = request.getMethod();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = (authentication != null) ? authentication.getName() : "Anonymous";

		UserLog userLog = UserLog.builder()
				.username(username)
				.method(method)
				.endpoint(endpoint)
				.time(LocalDateTime.now())
				.build();

		userLogRepository.save(userLog);

		filterChain.doFilter(request, response);

	}
}
