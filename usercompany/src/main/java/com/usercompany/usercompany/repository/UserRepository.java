package com.usercompany.usercompany.repository;

import com.usercompany.usercompany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
