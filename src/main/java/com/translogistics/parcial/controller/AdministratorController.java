package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.UsuarioRolConductorDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;
import com.translogistics.parcial.service.PersonaService;
import com.translogistics.parcial.service.RolService;
import com.translogistics.parcial.service.UsuarioService;
import com.translogistics.parcial.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/registroConductor")
    public String registrarConductor(@ModelAttribute UsuarioRolConductorDTO usuarioConductor, Model model) {
        UsuarioDTO usuario = usuarioConductor.getUsuarioDTO();
        RolConductorDTO rolDTO = usuarioConductor.getConductorDTO();
        if (rolDTO == null) {
            rolDTO = new RolConductorDTO();
        }
        rolDTO.setNombreRol("CONDUCTOR");

        PersonaDTO nuevaPersona = personaService.addPersonaInDB(usuario.getPersona());
        usuario.setPersona(nuevaPersona);
        RolConductor nuevoRol = new RolConductor(
                rolDTO.getId(),
                rolDTO.getNombreRol(),
                rolDTO.getLicencia(),
                rolDTO.getExperiencia());
        RolConductor rolGuardado = rolService.guardarRolConductor(nuevoRol);
        usuario.setRol(new RolConductorDTO(
                rolGuardado.getId(),
                rolGuardado.getNombreRol(),
                rolGuardado.getLicencia(),
                rolGuardado.getExperiencia()));

        usuarioService.addUsuarioInDB(usuario);
        return "redirect:/admin/registrarConductor";
    }

    @PostMapping("/registroVehiculo")
    public String registrarVehiculo(@ModelAttribute VehiculoDTO vehiculoDTO, Model model) {
        vehiculoService.addVehiculoInDB(vehiculoDTO);
        model.addAttribute("vehiculoDTO", new VehiculoDTO());
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
        RolDespachador rolGuardado = rolService
                .guardarRolDespachador(new RolDespachador(rolDTO.getId(), rolDTO.getNombreRol()));
        usuario.setPersona(nuevaPersona);
        usuario.setRol(new RolDespachadorDTO(rolGuardado.getId(), rolGuardado.getNombreRol()));
        usuarioService.addUsuarioInDB(usuario);
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "dispatcherRegistration";
    }
}
