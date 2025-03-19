package com.translogistics.parcial.service;

import com.translogistics.parcial.dto.VehiculoDTO;
import com.translogistics.parcial.mapper.VehiculoMapper;
import com.translogistics.parcial.model.Vehiculo;
import com.translogistics.parcial.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // 🔹 Agregar esta anotación para que Spring lo detecte
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoMapper vehiculoMapper;

    // Obtener todos los vehículos
    public List<VehiculoDTO> findAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos.stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }
    

    // Guardar un vehículo en la base de datos
    public VehiculoDTO addVehiculoInDB(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculoMapper.toEntity(vehiculoDTO));
        return vehiculoMapper.toDTO(vehiculoGuardado);
    }

    // Obtener vehículo por placa
    public ResponseEntity<VehiculoDTO> fetchVehiculoByPlaca(String placa) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(placa);
        if (vehiculo.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehiculoMapper.toDTO(vehiculo.get()), HttpStatus.OK);
    }
}
