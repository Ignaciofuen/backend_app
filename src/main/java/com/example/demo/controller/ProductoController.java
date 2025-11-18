package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "1. Gestión de Productos", description = "API para el CRUD de productos") 
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    
    @Operation(summary = "Obtener la lista de todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos devuelta con éxito")
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    
    @Operation(summary = "Obtener un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        try {
            Producto producto = productoService.getProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Crear un nuevo producto (o actualizar si se envía ID)")
    @ApiResponse(responseCode = "201", description = "Producto creado/actualizado exitosamente")
    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @Operation(summary = "Eliminar un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
           
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
   



    @Operation(summary = "Actualizar (editar) un producto existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(@PathVariable Long id, @RequestBody Producto productoDto) {
        try {
            Producto productoActualizado = productoService.updateProducto(id, productoDto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}