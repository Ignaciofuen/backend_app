package com.example.demo.service;

import com.example.demo.model.Usuario; 
import com.example.demo.model.entity.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuarioDto) {
       
        Optional<UsuarioEntity> existente = usuarioRepository.findByEmail(usuarioDto.getEmail());
        if (existente.isPresent()) {
            
            throw new RuntimeException("Error: El email ya está registrado");
        }
        
        UsuarioEntity nuevaEntidad = convertirA_Entidad(usuarioDto);
        UsuarioEntity entidadGuardada = usuarioRepository.save(nuevaEntidad);
    
        return convertirA_DTO(entidadGuardada);
    }


    public Usuario loginUsuario(String email, String password) {
    
        UsuarioEntity entidad = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Error: Email no registrado"));

        if (!entidad.getPassword().equals(password)) {
            throw new RuntimeException("Error: Contraseña incorrecta");
        }
        
        
        return convertirA_DTO(entidad);
    }

  
    public List<Usuario> getAllUsuarios() {
        List<UsuarioEntity> entidades = usuarioRepository.findAll();
    
        
        return entidades.stream()
                .map(this::convertirA_DTO) 
                .collect(Collectors.toList());
    }


    
    private Usuario convertirA_DTO(UsuarioEntity entidad) {
        return new Usuario(
                entidad.getIdusu(),
                entidad.getEmail(),
                null, 
                entidad.getRol()
        );
    }

    private UsuarioEntity convertirA_Entidad(Usuario dto) {
        return new UsuarioEntity(
                null, 
                dto.getEmail(),
                dto.getPassword(), 
                dto.getRol()
        );
    }
}