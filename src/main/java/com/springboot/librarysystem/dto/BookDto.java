package com.springboot.librarysystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BookDto {
	private Long id;

	@NotBlank(message = "Title is required")
	@Size(min = 2, max = 20, message = "Title must be between 2 and 20 characters")
	private String title;

	@NotBlank(message = "ISBN is required")
	@Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
	private String isbn;

	@NotBlank(message = "Edition is required")
	private String edition;

	@Min(value = 1500, message = "Publication year must be after 1500")
	@Max(value = 2025, message = "Publication year is invalid")
	private int publicationYear;

	@Size(max = 300, message = "Summary must not exceed 300 characters")
	private String summary;

	@Size(max = 100, message = "Cover Image URL must not exceed 100 characters")
	private String coverImageUrl;

	@NotNull(message = "Language is required")
	private Long languageId;

	@NotNull(message = "Publisher is required")
	private Long publisherId;

	@NotEmpty(message = "At least one author is required")
	private List<Long> authorIds;

	@NotEmpty(message = "At least one category is required")
	private List<Long> categoryIds;
}
