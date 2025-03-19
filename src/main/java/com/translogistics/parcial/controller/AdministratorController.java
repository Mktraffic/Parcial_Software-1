package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.*;
import com.translogistics.parcial.service.RegistroViajeService;
import com.translogistics.parcial.service.UsuarioService;
import com.translogistics.parcial.service.VehiculoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdministratorController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;

    
    @GetMapping("/admin/registroConductor")
    public String mostrarRegistroConductor(Model model) {
        model.addAttribute("conductor", new Usuario());
        return "driverRegistration";
    }

    @PostMapping("/registro/conductor")
    public ResponseEntity<UsuarioDTO> registrarConductor(@RequestBody UsuarioDTO conductor) {
        if (conductor.getRol().getNombreRol().equals("CONDUCTOR")) {
            UsuarioDTO nuevoConductor = usuarioService.addUsuarioInDB(conductor);
            return new ResponseEntity<>(nuevoConductor, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/admin/registroVehiculo")
    public String mostrarRegistroVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehicleRegistration";
    }

    @PostMapping("/registro/Vehiculo")
    public ResponseEntity<VehiculoDTO> registrarVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO nuevoVehiculo = vehiculoService.addVehiculoInDB(vehiculoDTO);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    @GetMapping("/admin/registroDespachador")
    public String mostrarRegistroDespachador(Model model) {
        model.addAttribute("despachador", new Usuario());
        return "dispatcherRegistration";
    }

    @PostMapping("/registro/Despachador")
    public ResponseEntity<UsuarioDTO> registrarDespachador(@RequestBody UsuarioDTO despachador) {
        if (despachador.getRol().getNombreRol().equals("DESPACHADOR")) {
            UsuarioDTO nuevoDespachador = usuarioService.addUsuarioInDB(despachador);
            return new ResponseEntity<>(nuevoDespachador, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/despachador/opciones")
    public String opcionesDespachador() {
        return "dispatcherOptions";
    }


 

    @PostMapping("/logout")
    public String cerrarSesion() {
        return "redirect:/";
    }

    @PostMapping("/dashboard/dispatcher")
    public String volverADispatcherOptions() {
        return "dispatcherOptions";
    }

    @PostMapping("/dashboard")
    public String volverAdministratorOptions() {
        return "administratorOptions";
    }
    @PostMapping("/registro/adm")
    public String volverRegistroAdm() {
        return "adminRegistration";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("login", new Usuario());
        return "userLogging";
    }
}
