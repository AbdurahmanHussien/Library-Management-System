package com.springboot.librarysystem.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthorDto {

    private Long id;

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 20, message = "name.short.or.long")
	private String name;

	@Size(max = 200, message = "bio.must.not.exceed.200.characters")
	private String bio;

}
