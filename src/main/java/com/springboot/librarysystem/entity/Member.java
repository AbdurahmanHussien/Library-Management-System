package com.springboot.librarysystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter @SuperBuilder

public class Member extends BaseEntity {

	private String email;

	private String phone;

	private String address;

	@Column(updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
}
