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
public class SystemAdminController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> registerAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoAdmin = usuarioService.addUsuarioInDB(usuarioDTO);
        return new ResponseEntity<>(nuevoAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getAdminById(@PathVariable Integer id) {
        return usuarioService.fetchUsuarioById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> getAllAdmins() {
        List<UsuarioDTO> admins = usuarioService.findAllUsuarios();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        boolean isAuthenticated = usuarioService.validateUserById(usuarioDTO.getId(), usuarioDTO.getPassword());

        if (isAuthenticated) {
            return new ResponseEntity<>("Login exitoso para el administrador con ID: " + usuarioDTO.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/dispatcher/login")
    public ResponseEntity<String> loginDispatcher(@RequestBody UsuarioDTO usuarioDTO) {
        boolean isAuthenticated = usuarioService.validateUserById(usuarioDTO.getId(), usuarioDTO.getPassword());

        if (isAuthenticated) {
            return new ResponseEntity<>("Login exitoso para el despachador con ID: " + usuarioDTO.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
    }

}
