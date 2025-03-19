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
public class connectionController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private RegistroViajeService registroViajeService;

    @GetMapping("/")
    public String mostrarRegistroAdministrador(Model model) {
        model.addAttribute("administrador", new Usuario());
        return "adminRegistration";
    }

    @PostMapping("/registro/administrador")
    public ResponseEntity<UsuarioDTO> registerAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getRol().getNombreRol().equals("ADMINISTRADOR")) {
            UsuarioDTO nuevoAdmin = usuarioService.addUsuarioInDB(usuarioDTO);
            return new ResponseEntity<>(nuevoAdmin, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

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

    @GetMapping("/dispatcher/asignarConductor")
    public String asignarConductor(Model model) {
        model.addAttribute("vehiculos", findAllPlatesVehicles());
        model.addAttribute("conductores", findAllDrivers());
        return "assignDriver";
    }

    public List<String> findAllDrivers() {
        List<UsuarioDTO> usuarios = usuarioService.findAllUsuarios();
        List<String> drivers = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getRol().getNombreRol().equals("CONDUCTOR")) {
                drivers.add(
                        usuarios.get(i).getPersona().getNombre() + " " + usuarios.get(i).getPersona().getApellido());
            }
        }
        return drivers;
    }

    public List<String> findAllPlatesVehicles() {
        List<VehiculoDTO> vehiculos = vehiculoService.findAllVehiculos();
        List<String> plates = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            plates.add(vehiculos.get(i).getPlaca() + " - " + vehiculos.get(i).getTipo());
        }
        return plates;
    }

    @PostMapping("/asignar")
    public ResponseEntity<RegistroViajeDTO> procesarAsignacion(@ModelAttribute("vehiculoPlaca") String placaVehiculo,
            @ModelAttribute("conductorNombre") String nombreConductor) {

        ResponseEntity<VehiculoDTO> vehiculoResponse = vehiculoService.fetchVehiculoByPlaca(placaVehiculo);
        if (vehiculoResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        VehiculoDTO vehiculoDTO = vehiculoResponse.getBody();

        // Buscar el conductor por nombre
        List<UsuarioDTO> response = usuarioService.findAllUsuarios();
        UsuarioDTO conductor = null;
        for (UsuarioDTO usuarioDTO : response) {
            String name = usuarioDTO.getPersona().getNombre() + " " + usuarioDTO.getPersona().getApellido();
            if (nombreConductor.equals(name) && usuarioDTO.getRol().getNombreRol().equals("CONDUCTOR")) {
                conductor = usuarioDTO;
                break;
            }
        }

        if (conductor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Crear el registro de viaje
        RegistroViajeDTO registroViajeDTO = new RegistroViajeDTO();
        registroViajeDTO.setFechaViaje(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        registroViajeDTO.setVehiculo(vehiculoDTO);
        registroViajeDTO.setConductor(conductor);

        // Guardar el registro de viaje en la base de datos
        RegistroViajeDTO savedRegistroViajeDTO = registroViajeService.addRegistroViaje(registroViajeDTO);

        return new ResponseEntity<>(savedRegistroViajeDTO, HttpStatus.CREATED);
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

    @PostMapping("/loggeo")
    public ResponseEntity<String> procesarLogin(@ModelAttribute("login") UsuarioDTO usuarioDTO) {
        boolean isAuthenticated = usuarioService.validateUserById(usuarioDTO.getId(), usuarioDTO.getUser_password());

        if (isAuthenticated) {
            return new ResponseEntity<>(
                    "Login exitoso para el " + usuarioDTO.getRol().getNombreRol() + " con ID: " + usuarioDTO.getId(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
    }
}
