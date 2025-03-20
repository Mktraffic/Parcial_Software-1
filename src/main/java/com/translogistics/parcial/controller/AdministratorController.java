package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDTO;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
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
    public String registrarConductor(@ModelAttribute UsuarioDTO usuario, Model model) {
    
        PersonaDTO persona = new PersonaDTO(
            usuario.getPersona().getId(),
            usuario.getPersona().getNombre(),
            usuario.getPersona().getApellido());
        PersonaDTO nuevaPersona = personaService.addPersonaInDB(persona);
    
        usuario.getRol().setNombreRol("CONDUCTOR");
    
        if (usuario.getRol() instanceof RolConductorDTO) {
            RolConductorDTO rolConductorDTO = (RolConductorDTO) usuario.getRol();
    
            RolConductor nuevoRol = new RolConductor(
                    rolConductorDTO.getId(),
                    rolConductorDTO.getNombreRol(),
                    rolConductorDTO.getLicencia(),
                    rolConductorDTO.getExperiencia());
    
            RolConductor rolGuardado = rolService.guardarRolConductor(nuevoRol);
            
            usuario.setPersona(nuevaPersona);
            usuario.setRol(new RolConductorDTO(rolGuardado.getId(), rolGuardado.getNombreRol(),
                    rolGuardado.getLicencia(), rolGuardado.getExperiencia()));
            
                    UsuarioDTO nuevoUsuario = usuarioService.addUsuarioInDB(usuario);
        model.addAttribute("usuarioDTO", nuevoUsuario);
        }
    
        return "driverRegistration";
    }

    @PostMapping("/registroVehiculo")
    public String registrarVehiculo(@ModelAttribute VehiculoDTO vehiculoDTO, Model model) {
        VehiculoDTO nuevoVehiculo = vehiculoService.addVehiculoInDB(vehiculoDTO);
        model.addAttribute("vehiculoDTO", nuevoVehiculo);
        return "vehicleRegistration";
    }

    @PostMapping("/registro/Despachador")
public String registrarDespachador(@ModelAttribute UsuarioDTO usuario, Model model) {

    PersonaDTO nuevaPersona = personaService.addPersonaInDB(usuario.getPersona());
    RolDespachadorDTO rolDTO;
    if (usuario.getRol() instanceof RolDespachadorDTO) {
        rolDTO = (RolDespachadorDTO) usuario.getRol();
    } else {
        rolDTO = new RolDespachadorDTO();
    }

    rolDTO.setNombreRol("DESPACHADOR");

    
    RolDespachador rolGuardado = rolService.guardarRolDespachador(new RolDespachador(rolDTO.getId(), rolDTO.getNombreRol()));

    usuario.setPersona(nuevaPersona);
    usuario.setRol(new RolDespachadorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));

    model.addAttribute("usuarioDTO", usuarioService.addUsuarioInDB(usuario));

    return "dispatcherRegistration";
}

}
