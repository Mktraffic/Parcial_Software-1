package com.translogistics.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.UsuarioRolConductorDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.service.PersonaService;
import com.translogistics.parcial.service.RolService;
import com.translogistics.parcial.service.UsuarioService;

@Controller
public class SystemController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private RolService rolService;

    @PostMapping("/registro/administrador")
    public String registerAdmin(@ModelAttribute UsuarioDTO administrador, Model model) {
        PersonaDTO persona = new PersonaDTO(
                administrador.getPersona().getId(),
                administrador.getPersona().getNombre(),
                administrador.getPersona().getApellido());

        PersonaDTO nuevaPersona = personaService.addPersonaInDB(persona);
        RolAdministradorDTO rolAdministradorDTO;
        if (administrador.getRol() instanceof RolAdministradorDTO) {
            rolAdministradorDTO = (RolAdministradorDTO) administrador.getRol();
        } else {
            rolAdministradorDTO = new RolAdministradorDTO();
            rolAdministradorDTO.setNombreRol("ADMINISTRADOR");
        }

        RolAdministrador nuevoRol = new RolAdministrador(
                rolAdministradorDTO.getId(),
                rolAdministradorDTO.getNombreRol());

        RolAdministrador rolGuardado = rolService.guardarRolAdministrador(nuevoRol);

        administrador.setPersona(nuevaPersona);
        administrador.setRol(new RolAdministradorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));

        usuarioService.addUsuarioInDB(administrador);
        model.addAttribute("mensaje", "Administrador registrado correctamente.");
        model.addAttribute("usuarioDTO", new UsuarioDTO());

        return "adminRegistration";
    }

    @PostMapping("/loggeo")
    public String procesarLogin(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        boolean isAuthenticated = usuarioService.validateUserById(usuarioDTO.getId(), usuarioDTO.getUser_password());

        if (isAuthenticated) {
            model.addAttribute("mensaje",
                    "Login exitoso para el " + usuarioDTO.getRol().getNombreRol() + " con ID: " + usuarioDTO.getId());
            String rol = usuarioDTO.getRol().getNombreRol();
            switch (rol.toUpperCase()) {
                case "ADMINISTRADOR":
                    return "administratorOptions";
                case "DESPACHADOR":
                    return "dispatcherOptions";
                case "CONDUCTOR":
                    model.addAttribute("error", "Sin acceso al sistema");
                    break;
            }
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "/login";
    }
    // Navegación entre vistas

    @GetMapping("/")
    public String mostrarFormularioAdmin(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "adminRegistration";
    }

    @GetMapping("/despachador/opciones")
    public String opcionesDespachador(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "dispatcherOptions";
    }

    @PostMapping("/logout")
    public String cerrarSesion() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard/dispatcher")
    public String volverADispatcherOptions(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "dispatcherOptions";
    }

    @GetMapping("/dashboard")
    public String volverAdministratorOptions(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "administratorOptions";
    }

    @GetMapping("/dashboard/admRegistration")
    public String volverAdministratorRegistration(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "adminRegistration";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "userLogging";
    }

    @GetMapping("/admin/registrarDespachador")
    public String mostrarFormularioDespachador(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "dispatcherRegistration";
    }

    @GetMapping("/admin/registrarVehiculo")
    public String mostrarRegistroVehiculo(Model model) {
        model.addAttribute("vehiculoDTO", new VehiculoDTO());
        return "vehicleRegistration";
    }

    @GetMapping("/admin/registrarConductor")
    public String mostrarRegistroConductor(Model model) {
        model.addAttribute("usuarioRolConductorDTO", new UsuarioRolConductorDTO());
        return "driverRegistration";
    }

}
