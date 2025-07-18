package com.springboot.librarysystem.entity;

import com.springboot.librarysystem.constants.Languages;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter @Builder
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private Languages name;

	public Language(Languages name) {
		this.name = name;
	}
}

