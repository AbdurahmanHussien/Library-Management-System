package com.springboot.librarysystem.controller;


import com.springboot.librarysystem.service.UserLogService;
import com.springboot.librarysystem.dto.PublisherDto;
import com.springboot.librarysystem.service.IPublisherService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {

	private final IPublisherService publisherService;
	private final UserLogService userLogService;



	@GetMapping
	@Operation(summary = "Get all publishers")
	public ResponseEntity<List<PublisherDto>> getAllPublishers() {
		List<PublisherDto> publishers = publisherService.getAllPublishers();
		return ResponseEntity.ok().body(publishers);

	}

	@GetMapping("/{id}")
	@Operation(summary = "Get publisher by id")
	public ResponseEntity<PublisherDto> getAuthorById(@PathVariable Long id) {
		PublisherDto publisher = publisherService.getPublisherById(id);
		return ResponseEntity.ok().body(publisher);
	}

	@PostMapping
	@Operation(summary = "Create publisher")
	public ResponseEntity<PublisherDto> createAuthor(@Valid @RequestBody PublisherDto publisherDto) {
		PublisherDto publisher = publisherService.addPublisher(publisherDto);
		userLogService.logUserAction("Post", "Create publisher with name: " + publisherDto.getName());
		return ResponseEntity.ok().body(publisher);
	}

	@PutMapping
	@Operation(summary = "Update publisher")
	public ResponseEntity<PublisherDto> updateAuthor(@Valid @RequestBody PublisherDto publisherDto) {
		PublisherDto publisher = publisherService.updatePublisher(publisherDto);
		userLogService.logUserAction("Put", "Update publisher with id: " + publisherDto.getId());
		return ResponseEntity.ok().body(publisher);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete publisher")
	public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
		publisherService.deletePublisher(id);
		userLogService.logUserAction("Delete", "Delete publisher with id: " + id);
		return ResponseEntity.ok("publisher deleted successfully");
	}

}
