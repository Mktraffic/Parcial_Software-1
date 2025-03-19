package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.service.UsuarioService;
import com.translogistics.parcial.service.VehiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdministratorController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;

    
    @PostMapping("/registro/conductor")
    public ResponseEntity<UsuarioDTO> registrarConductor(@RequestBody UsuarioDTO conductor) {
        if (conductor.getRol().getNombreRol().equals("CONDUCTOR")) {
            UsuarioDTO nuevoConductor = usuarioService.addUsuarioInDB(conductor);
            return new ResponseEntity<>(nuevoConductor, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registro/Vehiculo")
    public ResponseEntity<VehiculoDTO> registrarVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO nuevoVehiculo = vehiculoService.addVehiculoInDB(vehiculoDTO);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    @PostMapping("/registro/Despachador")
    public ResponseEntity<UsuarioDTO> registrarDespachador(@RequestBody UsuarioDTO despachador) {
        if (despachador.getRol().getNombreRol().equals("DESPACHADOR")) {
            UsuarioDTO nuevoDespachador = usuarioService.addUsuarioInDB(despachador);
            return new ResponseEntity<>(nuevoDespachador, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
