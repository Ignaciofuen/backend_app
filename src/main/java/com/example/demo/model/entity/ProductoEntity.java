package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor; 
import lombok.AllArgsConstructor;

@Entity(name = "productos") 
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "nombre") 
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria") 
    private String categoria;

    @Column(name = "imagen_url") 
    private String imagen;

    @Column(name = "precio")
    private int precio;
}