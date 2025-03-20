package com.translogistics.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.Usuario;
import com.translogistics.parcial.model.Vehiculo;
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

    @GetMapping("/")
    public String mostrarRegistroAdministrador(Model model) {
        model.addAttribute("administrador", new Usuario());
        return "adminRegistration";
    }

    @PostMapping("/registro/administrador")
    public String registerAdmin(@ModelAttribute UsuarioDTO administrador, Model model) {
        if (administrador.getPersona() == null) {
            model.addAttribute("error", "Debe ingresar los datos de la persona.");
            return "adminRegistration";
        }
    
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

        UsuarioDTO nuevoUsuario = usuarioService.addUsuarioInDB(administrador);
        model.addAttribute("usuarioDTO", nuevoUsuario);
        model.addAttribute("mensaje", "Administrador registrado correctamente.");
    
        return "adminRegistration";
    }

    @PostMapping("/loggeo")
    public String procesarLogin(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        boolean isAuthenticated = usuarioService.validateUserById(usuarioDTO.getId(), usuarioDTO.getUser_password());
    
        if (isAuthenticated) {
            model.addAttribute("mensaje", "Login exitoso para el " + usuarioDTO.getRol().getNombreRol() + " con ID: " + usuarioDTO.getId());
            return "dashboard"; 
        }
    
        model.addAttribute("error", "Credenciales incorrectas");
        return "";//Hay que determinar que rol de usuario de esta loggeando
    }
    // Navegación entre vistas

    @PostMapping("/")
    public String mostrarFormularioAdmin(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "adminRegistration";
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

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "userLogging";
    }

    @GetMapping("/admin/registroDespachador")
    public String mostrarFormularioDespachador(Model model) {
        model.addAttribute("usuarioDTO",  new UsuarioDTO());
        return "dispatcherRegistration";
    }

    @GetMapping("/admin/registroVehiculo")
    public String mostrarRegistroVehiculo(Model model) {
        model.addAttribute("vehiculoDTO", new VehiculoDTO());
        return "vehicleRegistration";
    }

    @GetMapping("/admin/registrarConductor")
    public String mostrarRegistroConductor(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "driverRegistration";
    }

}
