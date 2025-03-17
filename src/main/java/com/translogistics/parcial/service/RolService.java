package com.translogistics.parcial.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.dto.RolConductorDTO;
import com.translogistics.parcial.dto.RolDTO;
import com.translogistics.parcial.dto.RolDespachadorDTO;
import com.translogistics.parcial.mapper.RolAdministradorMapper;
import com.translogistics.parcial.mapper.RolConductorMapper;
import com.translogistics.parcial.mapper.RolDespachadorMapper;
import com.translogistics.parcial.mapper.RolMapper;
import com.translogistics.parcial.model.Rol;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;
import com.translogistics.parcial.repository.RolAdministradorRepository;
import com.translogistics.parcial.repository.RolConductorRepository;
import com.translogistics.parcial.repository.RolDespachadorRepository;

@Service
public class RolService {

    private final RolAdministradorRepository rolAdministradorRepository;
    private final RolConductorRepository rolConductorRepository;
    private final RolDespachadorRepository rolDespachadorRepository;
    private final RolMapper rolMapper;
    private final RolAdministradorMapper rolAdministradorMapper;
    private final RolConductorMapper rolConductorMapper;
    private final RolDespachadorMapper rolDespachadorMapper;

     @Autowired
    public RolService(
        RolAdministradorRepository rolAdministradorRepository,
        RolConductorRepository rolConductorRepository,
        RolDespachadorRepository rolDespachadorRepository,
        RolMapper rolMapper,
        RolAdministradorMapper rolAdministradorMapper,
        RolConductorMapper rolConductorMapper,
        RolDespachadorMapper rolDespachadorMapper
    ) {
        this.rolAdministradorRepository = rolAdministradorRepository;
        this.rolConductorRepository = rolConductorRepository;
        this.rolDespachadorRepository = rolDespachadorRepository;
        this.rolMapper = rolMapper;
        this.rolAdministradorMapper = rolAdministradorMapper;
        this.rolConductorMapper = rolConductorMapper;
        this.rolDespachadorMapper = rolDespachadorMapper;
    }

    public ArrayList<RolConductorDTO> obtenerRolesConductor() {
        return rolConductorRepository.findAll().stream()
            .map(rolConductorMapper::toDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RolAdministradorDTO> obtenerRolesAdministrador() {
        return rolAdministradorRepository.findAll().stream()
            .map(rolAdministradorMapper::toDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RolDespachadorDTO> obtenerRolesDespachador() {
        return rolDespachadorRepository.findAll().stream()
            .map(rolDespachadorMapper::toDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RolDTO> obtenerTodosLosRoles() {
        ArrayList<RolDTO> roles = new ArrayList<>();
        roles.addAll(rolAdministradorRepository.findAll().stream()
            .map(rolMapper::toDTO)
            .collect(Collectors.toList()));
        roles.addAll(rolConductorRepository.findAll().stream()
            .map(rolMapper::toDTO)
            .collect(Collectors.toList()));
        roles.addAll(rolDespachadorRepository.findAll().stream()
            .map(rolMapper::toDTO)
            .collect(Collectors.toList()));
        return roles;
    }

    public Optional<? extends RolDTO> obtenerRolPorId(int id) {
        if (rolAdministradorRepository.findById(id).isPresent()) {
            return rolAdministradorRepository.findById(id).map(rolAdministradorMapper::toDTO);
        } else if (rolConductorRepository.findById(id).isPresent()) {
            return rolConductorRepository.findById(id).map(rolConductorMapper::toDTO);
        } else if (rolDespachadorRepository.findById(id).isPresent()) {
            return rolDespachadorRepository.findById(id).map(rolDespachadorMapper::toDTO);
        }
        return Optional.empty();
    }

    public RolAdministradorDTO guardarRolAdministrador(RolAdministradorDTO rolAdministradorDTO) {
        RolAdministrador rolAdministrador = rolAdministradorMapper.toEntity(rolAdministradorDTO);
        return rolAdministradorMapper.toDTO(rolAdministradorRepository.save(rolAdministrador));
    }

    public RolConductorDTO guardarRolConductor(RolConductorDTO rolConductorDTO) {
        RolConductor rolConductor = rolConductorMapper.toEntity(rolConductorDTO);
        return rolConductorMapper.toDTO(rolConductorRepository.save(rolConductor));
    }

    public RolDespachadorDTO guardarRolDespachador(RolDespachadorDTO rolDespachadorDTO) {
        RolDespachador rolDespachador = rolDespachadorMapper.toEntity(rolDespachadorDTO);
        return rolDespachadorMapper.toDTO(rolDespachadorRepository.save(rolDespachador));
    }
}
