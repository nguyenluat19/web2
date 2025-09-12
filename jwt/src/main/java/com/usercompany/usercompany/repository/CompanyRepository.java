package com.usercompany.usercompany.repository;

import com.usercompany.usercompany.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
