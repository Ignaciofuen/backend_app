package com.example.demo.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Producto {
    private Long id;
    private String nombre; 
    private String descripcion;
    private String categoria; 
    private String imagen; 
    private int precio;
}