package com.bt2.bt2.repository;

import com.bt2.bt2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
