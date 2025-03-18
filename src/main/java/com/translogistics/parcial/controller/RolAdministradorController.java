package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.service.RolService;
import com.translogistics.parcial.service.UsuarioService;
import com.translogistics.parcial.service.VehiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rolAdministrador")
@CrossOrigin
public class RolAdministradorController {

    @Autowired
    private UsuarioService userService;

    @Autowired
    private VehiculoService vehiculoService;

    /*
     * @GetMapping("/conductores")
     * public ResponseEntity<List<RolConductorDTO>> obtenerConductores() {
     * List<RolConductorDTO> conductores = rolService.obtenerRolesConductor();
     * return new ResponseEntity<>(conductores, HttpStatus.OK);
     * }
     */

    @PostMapping("/agregarConductor")
    public ResponseEntity<UsuarioDTO> registrarConductor(@RequestBody UsuarioDTO conductor) {
        if (conductor.getRol().getNombreRol().equals("CONDUCTOR")) {
            UsuarioDTO nuevoConductor = userService.addUsuarioInDB(conductor);
            return new ResponseEntity<>(nuevoConductor, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/buscarConductor/{id}")
    public ResponseEntity<UsuarioDTO> buscarConductorPorId(@PathVariable Long id) {
        List<UsuarioDTO> response = userService.findAllUsuarios();
        for (UsuarioDTO usuarioDTO : response) {
            if (usuarioDTO.getRol().getId().equals(id) && usuarioDTO.getRol().getNombreRol().equals("CONDUCTOR")) {
                return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/agregarDespachador")
    public ResponseEntity<UsuarioDTO> registrarDespachador(@RequestBody UsuarioDTO despachador) {
        if (despachador.getRol().getNombreRol().equals("DESPACHADOR")) {
            UsuarioDTO nuevoDespachador = userService.addUsuarioInDB(despachador);
            return new ResponseEntity<>(nuevoDespachador, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/buscarDespachador/{id}")
    public ResponseEntity<UsuarioDTO> buscarDespachadorPorId(@PathVariable Long id) {
        List<UsuarioDTO> response = userService.findAllUsuarios();
        for (UsuarioDTO usuarioDTO : response) {
            if (usuarioDTO.getRol().getId().equals(id) && usuarioDTO.getRol().getNombreRol().equals("DESPACHADOR")) {
                return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/vehiculos")
    public ResponseEntity<List<VehiculoDTO>> obtenerTodosLosVehiculos() {
        List<VehiculoDTO> vehiculos = vehiculoService.findAllVehiculos();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @PostMapping("/agregarVehiculo")
    public ResponseEntity<VehiculoDTO> registrarVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO nuevoVehiculo = vehiculoService.addVehiculoInDB(vehiculoDTO);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    @GetMapping("/buscarVehiculo/{placa}")
    public ResponseEntity<VehiculoDTO> buscarVehiculoPorPlaca(@PathVariable String placa) {
        ResponseEntity<VehiculoDTO> vehiculo = vehiculoService.fetchVehiculoByPlaca(placa);
        return vehiculo;
    }
}
