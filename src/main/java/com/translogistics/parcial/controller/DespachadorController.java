package com.translogistics.parcial.controller;

import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.service.RegistroViajeService;
import com.translogistics.parcial.service.RolService;
import com.translogistics.parcial.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/despachador")
public class DespachadorController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private RolService rolService;

    @Autowired
    private RegistroViajeService registroViajeService;

    @PostMapping("/asignar")
    public ResponseEntity<RegistroViajeDTO> asignarVehiculoAConductor(
            @RequestParam String placaVehiculo,
            @RequestParam int idConductor) {

        // Buscar el vehículo por placa
        ResponseEntity<VehiculoDTO> vehiculoResponse = vehiculoService.fetchVehiculoByPlaca(placaVehiculo);
        if (vehiculoResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        VehiculoDTO vehiculoDTO = vehiculoResponse.getBody();

        // Buscar el conductor por ID
        Optional<? extends RolDTO> conductorOptional = rolService.obtenerRolPorId(idConductor);
        if (conductorOptional.isEmpty() || !(conductorOptional.get() instanceof RolConductorDTO)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        RolConductorDTO conductorDTO = (RolConductorDTO) conductorOptional.get();

        // Crear el registro de viaje
        RegistroViajeDTO registroViajeDTO = new RegistroViajeDTO();
        registroViajeDTO.setFechaViaje(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        registroViajeDTO.setVehiculo(vehiculoDTO);
        registroViajeDTO.setConductor(conductorDTO);

        // Guardar el registro de viaje en la base de datos
        RegistroViajeDTO savedRegistroViajeDTO = registroViajeService.addRegistroViaje(registroViajeDTO);

        return new ResponseEntity<>(savedRegistroViajeDTO, HttpStatus.CREATED);
    }
}