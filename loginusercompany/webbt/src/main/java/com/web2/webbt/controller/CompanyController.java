package com.web2.webbt.controller;

import com.web2.webbt.model.Company;
import com.web2.webbt.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyRepository companyRepository;

    @GetMapping
    public List<Company> all() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable Long id) {
        return companyRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyRepository.save(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!companyRepository.existsById(id)) return ResponseEntity.notFound().build();
        companyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
