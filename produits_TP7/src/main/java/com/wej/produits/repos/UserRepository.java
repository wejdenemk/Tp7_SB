package com.wej.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wej.produits.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername (String username);
	}
