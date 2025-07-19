package com.springboot.librarysystem.config;

import com.springboot.librarysystem.service.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


			private final CustomUserDetailsService userDetailsService;

			private static final String[] AUTH_WHITELIST = {

					"/v3/api-docs/**",
					"/swagger-ui/**",
					"/swagger-resources/**",
					"/v2/api-docs",
					"/webjars/**"
			};


	@Bean
	public SecurityFilterChain defaultsecurityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(https ->
						https
								.requestMatchers(HttpMethod.GET, "/book/**", "/member/**", "/borrowing/**", "/author/**", "/category/**", "/language/**", "/publisher/**", "/userLog/**")
								.hasAnyRole("ADMINISTRATOR", "LIBRARIAN", "STAFF") //staff can only GET books, members, borrowings, authors, categories, languages, publishers
								.requestMatchers(AUTH_WHITELIST).permitAll()
								.requestMatchers("/users/**", "/author/**", "/category/**", "/language/**", "/publisher/**").hasRole("ADMINISTRATOR") // only admin can add authors, categories, languages, publishers, users(or Accounts)
								.requestMatchers("/book/**", "/member/**", "/borrowing/**").hasAnyRole("ADMINISTRATOR", "LIBRARIAN") // only admin and librarian can add books, members, borrowings
				)
				.httpBasic(Customizer.withDefaults())
				.authenticationProvider(authenticationProvider());

		return http.build();
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

}
