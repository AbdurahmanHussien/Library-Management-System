package com.springboot.librarysystem.service.security;

import com.springboot.librarysystem.entity.auth.User;
import com.springboot.librarysystem.exception.ResourceNotFoundException;
import com.springboot.librarysystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("user.not.found");
		}

		return new CustomUserDetails(user);

	}
}
