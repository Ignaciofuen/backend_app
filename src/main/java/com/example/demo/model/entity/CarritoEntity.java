package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "carritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Cada carrito pertenece a un solo usuario
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    // Un carrito puede tener muchos productos
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "carrito_productos",
        joinColumns = @JoinColumn(name = "carrito_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoEntity> productos = new ArrayList<>();
}