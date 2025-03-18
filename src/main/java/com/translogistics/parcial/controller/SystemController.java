package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class SystemController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> registerAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        if(usuarioDTO.getRol().getNombreRol().equals("ADMINISTRADOR")){
        UsuarioDTO nuevoAdmin = usuarioService.addUsuarioInDB(usuarioDTO);
        return new ResponseEntity<>(nuevoAdmin, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarAdministradorPorId(@PathVariable Long id) {
        List<UsuarioDTO> response = usuarioService.findAllUsuarios();
        for (UsuarioDTO usuarioDTO : response) {
            if (usuarioDTO.getRol().getId().equals(id) && usuarioDTO.getRol().getNombreRol().equals("ADMINISTRADOR")) {
                return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        List<UsuarioDTO> admins = usuarioService.findAllUsuarios();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UsuarioDTO usuarioDTO) {
        boolean isAuthenticated = usuarioService.validateUserById(usuarioDTO.getId(), usuarioDTO.getPassword());

        if (isAuthenticated) {
            return new ResponseEntity<>("Login exitoso para el " + usuarioDTO.getRol().getNombreRol() + " con ID: " + usuarioDTO.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
    }

}
