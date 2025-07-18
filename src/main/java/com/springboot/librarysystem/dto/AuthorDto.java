package com.springboot.librarysystem.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthorDto {

	@Schema(description = "ID of the author", example = "1")
    private Long id;

	@NotBlank(message = "name.is.required")
	@Size(min = 3, max = 20, message = "name.short.or.long")
	@Schema(description = "Name of the author", example = "John Doe")
	private String name;

	@Size(max = 200, message = "bio.must.not.exceed.200.characters")
	@Schema(description = "Biography of the author", example = "John Doe is a famous author.")
	private String bio;

}
