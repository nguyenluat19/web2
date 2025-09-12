package com.usercompany.usercompany.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // vì MySQL có từ khóa user
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private String role = "USER";
}
