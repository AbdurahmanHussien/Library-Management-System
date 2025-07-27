package com.springboot.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLog  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String ipAddress;

	private String actionType;

	private String details;

	private LocalDateTime time;



	@PrePersist
	public void prePersist() {
		time = LocalDateTime.now();
	}
}
