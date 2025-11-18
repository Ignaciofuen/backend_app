package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.model.entity.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

  
    public List<Producto> getAllProductos(){
        List<ProductoEntity> entidades = productoRepository.findAll();
        return entidades.stream().map(this::convertirA_DTO).collect(Collectors.toList());
    }

 
    public Producto getProductoById(Long id) {
        ProductoEntity entidad = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertirA_DTO(entidad);
    }

   
    public Producto saveProducto(Producto productoDto) {

        ProductoEntity entidad = convertirA_Entidad(productoDto);
        ProductoEntity entidadGuardada = productoRepository.save(entidad);
        return convertirA_DTO(entidadGuardada);
    }


    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Error: No se encontró el producto con ID " + id);
        }
        productoRepository.deleteById(id);
    }

   
    public Producto updateProducto(Long id, Producto productoDto) {
        
        ProductoEntity entidadExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el producto con ID " + id));

        
        entidadExistente.setNombre(productoDto.getNombre());
        entidadExistente.setDescripcion(productoDto.getDescripcion());
        entidadExistente.setCategoria(productoDto.getCategoria());
        entidadExistente.setPrecio(productoDto.getPrecio());
        if (productoDto.getImagen() != null && !productoDto.getImagen().isEmpty()) {
            entidadExistente.setImagen(productoDto.getImagen());
        }

    
        ProductoEntity entidadGuardada = productoRepository.save(entidadExistente);

        
        return convertirA_DTO(entidadGuardada);
    }


    
    private Producto convertirA_DTO(ProductoEntity entidad) {
        return new Producto(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion(),
                entidad.getCategoria(),
                entidad.getImagen(),
                entidad.getPrecio()
        );
    }

  
    private ProductoEntity convertirA_Entidad(Producto dto) {
     
        Long id = (dto.getId() != null && dto.getId() != 0) ? dto.getId() : null;
        
        return new ProductoEntity(
                id, 
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getCategoria(),
                dto.getImagen(),
                dto.getPrecio()
        );
    }

}