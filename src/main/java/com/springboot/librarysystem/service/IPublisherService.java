package com.springboot.librarysystem.service;

import com.springboot.librarysystem.dto.PublisherDto;
import java.util.List;


public interface IPublisherService {
	PublisherDto addPublisher(PublisherDto publisher);
	PublisherDto getPublisherById(Long id);
	PublisherDto updatePublisher(PublisherDto publisher);
	void deletePublisher(Long id);
	List<PublisherDto> getAllPublishers();
}
