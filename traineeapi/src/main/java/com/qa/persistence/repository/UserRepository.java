package com.qa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qa.persistence.domain.Trainee;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<Trainee, String> {

}
