package com.example.demo.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    private Long idusu;
    private String email;
    private String password; 
    private String rol;
}