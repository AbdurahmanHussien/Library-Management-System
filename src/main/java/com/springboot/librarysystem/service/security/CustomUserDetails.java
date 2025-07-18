package com.springboot.librarysystem.service.security;

import com.springboot.librarysystem.entity.auth.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {


		private final User user;



		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
					.collect(Collectors.toList());
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			return user.isActive();
		}

		@Override
		public boolean isAccountNonLocked() {
			return user.isActive();
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return user.isActive();
		}

		@Override
		public boolean isEnabled() {
			return user.isActive();
		}

	@Override
	public String toString() {
		return user.getUsername();
	}

}
