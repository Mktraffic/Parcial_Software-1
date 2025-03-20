package com.translogistics.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.Usuario;
import com.translogistics.parcial.model.Vehiculo;
import com.translogistics.parcial.service.PersonaService;
import com.translogistics.parcial.service.RolService;
import com.translogistics.parcial.service.UsuarioService;

@RestController
public class SystemController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private RolService rolService;
    
    @GetMapping("/")
    public String mostrarRegistroAdministrador(Model model) {
        model.addAttribute("administrador", new Usuario());
        return "adminRegistration";
    }

    @PostMapping("/registro/administrador")
    public ResponseEntity<UsuarioDTO> registerAdmin(@RequestBody UsuarioDTO administrador) {
        if (administrador.getPersona() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        PersonaDTO persona = new PersonaDTO(
            administrador.getPersona().getId(),
            administrador.getPersona().getNombre(),
            administrador.getPersona().getApellido());
        PersonaDTO nuevaPersona = personaService.addPersonaInDB(persona);

        if (administrador.getRol() instanceof RolAdministradorDTO) {
            RolAdministradorDTO rolAdministradorDTO = (RolAdministradorDTO) administrador.getRol();

            RolAdministrador nuevoRol = new RolAdministrador(
                rolAdministradorDTO.getId(),
                rolAdministradorDTO.getNombreRol());

                    RolAdministrador rolGuardado = rolService.guardarRolAdministrador(nuevoRol);
            administrador.setPersona(nuevaPersona);
            administrador.setRol(new RolAdministradorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));
            UsuarioDTO nuevoUsuario = usuarioService.addUsuarioInDB(administrador);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    //Navegación entre vistas
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
    @GetMapping("/admin/registroDespachador")
    public String mostrarRegistroDespachador(Model model) {
        model.addAttribute("despachador", new Usuario());
        return "dispatcherRegistration";
    }
    @GetMapping("/admin/registroVehiculo")
    public String mostrarRegistroVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehicleRegistration";
    }
    @GetMapping("/admin/registroConductor")
    public String mostrarRegistroConductor(Model model) {
        model.addAttribute("conductor", new Usuario());
        return "driverRegistration";
    }

}
