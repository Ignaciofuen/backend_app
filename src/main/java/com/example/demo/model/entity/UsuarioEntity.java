package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios") 
@Data
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long idusu;

    @Column(name = "email", unique = true) 
    private String email;

    @Column(name = "password") 
    private String password;

    @Column(name = "rol")
    private String rol;
}
