package com.springboot.librarysystem.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Author extends BaseEntity {

	private String bio;
}

