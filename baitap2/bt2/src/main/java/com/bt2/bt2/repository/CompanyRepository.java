package com.bt2.bt2.repository;

import com.bt2.bt2.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE company", nativeQuery = true)
    void truncateTable();
}
