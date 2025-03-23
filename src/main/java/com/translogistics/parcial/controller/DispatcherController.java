package com.translogistics.parcial.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.dto.UsuarioDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.service.RegistroViajeService;
import com.translogistics.parcial.service.UsuarioService;
import com.translogistics.parcial.service.VehiculoService;

@Controller
public class DispatcherController {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RegistroViajeService registroViajeService;

    @PostMapping("/asignar")
    public String procesarAsignacion(
            @ModelAttribute("vehiculoPlaca") String placaVehiculo,
            @ModelAttribute("conductorNombre") String nombreConductor,
            Model model) {

        ResponseEntity<VehiculoDTO> vehiculoResponse = vehiculoService.fetchVehiculoByPlaca(placaVehiculo);
        VehiculoDTO vehiculoDTO = vehiculoResponse.getBody();
        List<UsuarioDTO> usuarios = usuarioService.findAllUsuarios();

        UsuarioDTO conductor = usuarios.stream()
                .filter(usuario -> {
                    String fullName = usuario.getPersona().getNombre() + " " + usuario.getPersona().getApellido();
                    return nombreConductor.equals(fullName) && usuario.getRol().getNombreRol().equals("CONDUCTOR");
                })
                .findFirst()
                .orElse(null);

        RegistroViajeDTO registroViajeDTO = new RegistroViajeDTO();
        registroViajeDTO.setFechaViaje(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        registroViajeDTO.setVehiculo(vehiculoDTO);
        registroViajeDTO.setConductor(conductor);
        registroViajeService.addRegistroViaje(registroViajeDTO);

        return "assingDriver";
    }

    @GetMapping("/dispatcher/asignarConductor")
    public String asignarConductor(Model model) {
        List<String> vehiculos = findAllPlatesVehicles();
        List<String> conductores = findAllDrivers();
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("conductores", conductores);
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
}
