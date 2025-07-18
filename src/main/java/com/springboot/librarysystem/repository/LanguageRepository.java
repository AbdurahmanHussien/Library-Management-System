package com.springboot.librarysystem.repository;


import com.springboot.librarysystem.constants.Languages;
import com.springboot.librarysystem.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

	boolean existsLanguageByName(Languages name);


}
