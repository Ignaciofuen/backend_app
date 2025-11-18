package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.model.entity.CarritoEntity;
import com.example.demo.model.entity.ProductoEntity;
import com.example.demo.model.entity.UsuarioEntity;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    // se crea el carrito de un usuario
    private CarritoEntity obtenerCarritoEntidad(Long userId) {
        return carritoRepository.findByUsuarioIdusu(userId)
                .orElseGet(() -> {
                    // Si no existe, creamos uno nuevo
                    UsuarioEntity usuario = usuarioRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    CarritoEntity nuevoCarrito = new CarritoEntity();
                    nuevoCarrito.setUsuario(usuario);
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    public List<Producto> obtenerProductosDelCarrito(Long userId) {
        CarritoEntity carrito = obtenerCarritoEntidad(userId);
        return carrito.getProductos().stream()
                .map(this::convertirProductoADTO) 
                .collect(Collectors.toList());
    }


    public void agregarProducto(Long userId, Long productoId) {
        CarritoEntity carrito = obtenerCarritoEntidad(userId);
        ProductoEntity producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        carrito.getProductos().add(producto); 
        carritoRepository.save(carrito);      
    }

    public void eliminarProducto(Long userId, Long productoId) {
        CarritoEntity carrito = obtenerCarritoEntidad(userId);
        carrito.getProductos().removeIf(p -> p.getId().equals(productoId));
        carritoRepository.save(carrito);
    }

   
    public void vaciarCarrito(Long userId) {
        CarritoEntity carrito = obtenerCarritoEntidad(userId);
        carrito.getProductos().clear();
        carritoRepository.save(carrito);
    }

    
    private Producto convertirProductoADTO(ProductoEntity entidad) {
        return new Producto(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion(),
                entidad.getCategoria(),
                entidad.getImagen(),
                entidad.getPrecio()
        );
    }
}