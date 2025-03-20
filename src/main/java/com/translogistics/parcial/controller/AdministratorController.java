package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;
import com.translogistics.parcial.service.PersonaService;
import com.translogistics.parcial.service.RolService;
import com.translogistics.parcial.service.UsuarioService;
import com.translogistics.parcial.service.VehiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministratorController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private RolService rolService;

    @PostMapping("/registro/conductor")
    public ResponseEntity<UsuarioDTO> registrarConductor(@RequestBody UsuarioDTO conductor) {
        if (conductor.getPersona() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        PersonaDTO persona = new PersonaDTO(
                conductor.getPersona().getId(),
                conductor.getPersona().getNombre(),
                conductor.getPersona().getApellido());
        PersonaDTO nuevaPersona = personaService.addPersonaInDB(persona);
        if (conductor.getRol() instanceof RolConductorDTO) {
            RolConductorDTO rolConductorDTO = (RolConductorDTO) conductor.getRol();

            RolConductor nuevoRol = new RolConductor(
                    rolConductorDTO.getId(),
                    rolConductorDTO.getNombreRol(),
                    rolConductorDTO.getLicencia(),
                    rolConductorDTO.getExperiencia());

            RolConductor rolGuardado = rolService.guardarRolConductor(nuevoRol);
            conductor.setPersona(nuevaPersona);
            conductor.setRol(new RolConductorDTO(rolGuardado.getId(), rolGuardado.getNombreRol(),
                    rolGuardado.getLicencia(), rolGuardado.getExperiencia()));
            UsuarioDTO nuevoUsuario = usuarioService.addUsuarioInDB(conductor);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
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

        if (despachador.getPersona() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        PersonaDTO persona = new PersonaDTO(
                despachador.getPersona().getId(),
                despachador.getPersona().getNombre(),
                despachador.getPersona().getApellido());
        PersonaDTO nuevaPersona = personaService.addPersonaInDB(persona);

        if (despachador.getRol() instanceof RolDespachadorDTO) {
            RolDespachadorDTO rolDespachadorDTO = (RolDespachadorDTO) despachador.getRol();

            RolDespachador nuevoRol = new RolDespachador(
                    rolDespachadorDTO.getId(),
                    rolDespachadorDTO.getNombreRol());

            RolDespachador rolGuardado = rolService.guardarRolDespachador(nuevoRol);
            despachador.setPersona(nuevaPersona);
            despachador.setRol(new RolDespachadorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));
            UsuarioDTO nuevoUsuario = usuarioService.addUsuarioInDB(despachador);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
