package com.mlmstorenow.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlmstorenow.api.models.User;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{

	Optional<User> findByEmail(String email);
	
	Optional<User> findById(int id);
	
	
}
