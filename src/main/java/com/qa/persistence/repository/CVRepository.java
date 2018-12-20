package com.qa.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qa.persistence.domain.CV;

@Transactional
@Repository
public interface CVRepository extends CrudRepository<CV, Long> {

	Iterable<CV> findAllByAuthorName(String traineeName);

}
