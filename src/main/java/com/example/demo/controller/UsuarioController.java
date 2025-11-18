package com.example.demo.controller;
import com.example.demo.model.Usuario; 
import com.example.demo.service.UsuarioService;
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
@RequestMapping("/api/auth")
@Tag(name = "2. Autenticación y Usuarios", description = "API para registro, login y gestión de usuarios") 
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

   
    public static class LoginRequest {
        public String email;
        public String password;
    }
    
    @Operation(summary = "Registrar un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida (ej: email ya existe)")
    })
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuarioDto) {
        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRegistrado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

  
    @Operation(summary = "Iniciar sesión de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "401", description = "No autorizado (email o clave incorrectos)")
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuarioLogueado = usuarioService.loginUsuario(
                loginRequest.email, 
                loginRequest.password
            );
            return ResponseEntity.ok(usuarioLogueado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
    @Operation(summary = "Obtener la lista de todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios devuelta (sin claves)")
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
}