package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.service.RolService;
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
    private RolService rolService;
    
    @Autowired
    private VehiculoService vehiculoService;

    

    @GetMapping("/conductores")
    public ResponseEntity<List<RolConductorDTO>> obtenerConductores() {
        List<RolConductorDTO> conductores = rolService.obtenerRolesConductor();
        return new ResponseEntity<>(conductores, HttpStatus.OK);
    }

    @PostMapping("/agregarConductor")
    public ResponseEntity<RolConductorDTO> registrarConductor(@RequestBody RolConductorDTO conductorDTO) {
        RolConductorDTO nuevoConductor = rolService.guardarRolConductor(conductorDTO);
        return new ResponseEntity<>(nuevoConductor, HttpStatus.CREATED);
    }

  @GetMapping("/buscarConductor/{id}")
public ResponseEntity<RolConductorDTO> buscarConductorPorId(@PathVariable int id) {
    Optional<? extends RolDTO> rolDTO = rolService.obtenerRolPorId(id);

    if (rolDTO.isPresent() && rolDTO.get() instanceof RolConductorDTO) {
        return new ResponseEntity<>((RolConductorDTO) rolDTO.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@GetMapping("/despachadores")
public ResponseEntity<List<RolDespachadorDTO>> obtenerTodosLosDespachadores() {
    List<RolDespachadorDTO> despachadores = rolService.obtenerRolesDespachador();
    return new ResponseEntity<>(despachadores, HttpStatus.OK);
}

@PostMapping("/agregarDespachador")
public ResponseEntity<RolDespachadorDTO> registrarDespachador(@RequestBody RolDespachadorDTO despachadorDTO) {
    RolDespachadorDTO nuevoDespachador = rolService.guardarRolDespachador(despachadorDTO);
    return new ResponseEntity<>(nuevoDespachador, HttpStatus.CREATED);
}
@GetMapping("/buscarDespachador/{id}")
public ResponseEntity<RolDespachadorDTO> buscarDespachadorPorId(@PathVariable int id) {
    Optional<? extends RolDTO> rolDTO = rolService.obtenerRolPorId(id);
    if (rolDTO.isPresent() && rolDTO.get() instanceof RolDespachadorDTO) {
        return new ResponseEntity<>((RolDespachadorDTO) rolDTO.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
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


    
@GetMapping("/buscarVehiculo/{id}")
public ResponseEntity<VehiculoDTO> buscarVehiculoPorId(@PathVariable String id) {
    ResponseEntity<VehiculoDTO> vehiculo = vehiculoService.fetchVehiculoByPlaca(id);
    return vehiculo;
   
}






}

   


