package com.springboot.librarysystem.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter @SuperBuilder

public class Publisher extends BaseEntity {
	private String address;
	private String contactEmail;
}
