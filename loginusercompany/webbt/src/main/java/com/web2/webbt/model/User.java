package com.web2.webbt.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // store encoded

    private String fullName;
    private String email;
    private String role; // e.g. ROLE_USER, ROLE_ADMIN

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
