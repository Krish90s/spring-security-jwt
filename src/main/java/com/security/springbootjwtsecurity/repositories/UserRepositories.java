package com.security.springbootjwtsecurity.repositories;

import com.security.springbootjwtsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
