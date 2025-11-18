package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@Tag(name = "3. Carrito de Compras", description = "Gestión del carrito por usuario")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Operation(summary = "Obtener los productos del carrito de un usuario")
    @GetMapping("/{userId}")
    public List<Producto> verCarrito(@PathVariable Long userId) {
        return carritoService.obtenerProductosDelCarrito(userId);
    }

    
    @Operation(summary = "Agregar un producto al carrito")
    @PostMapping("/{userId}/agregar/{productoId}")
    public ResponseEntity<Void> agregarProducto(@PathVariable Long userId, @PathVariable Long productoId) {
        carritoService.agregarProducto(userId, productoId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Eliminar un producto del carrito")
    @DeleteMapping("/{userId}/quitar/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long userId, @PathVariable Long productoId) {
        carritoService.eliminarProducto(userId, productoId);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Vaciar el carrito completo")
    @DeleteMapping("/{userId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long userId) {
        carritoService.vaciarCarrito(userId);
        return ResponseEntity.ok().build();
    }
}