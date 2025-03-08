package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY ) // Указываешь имя поля в классе User, которое будет связано с этой таблицей
    private Set<User> users = new LinkedHashSet<>();
}
