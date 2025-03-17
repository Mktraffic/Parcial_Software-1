package com.translogistics.parcial.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.translogistics.parcial.dto.RolDTO;
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

    @Autowired
    public RolService(
        RolAdministradorRepository rolAdministradorRepository,
        RolConductorRepository rolConductorRepository,
        RolDespachadorRepository rolDespachadorRepository,
        RolMapper rolMapper
    ) {
        this.rolAdministradorRepository = rolAdministradorRepository;
        this.rolConductorRepository = rolConductorRepository;
        this.rolDespachadorRepository = rolDespachadorRepository;
        this.rolMapper = rolMapper;
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

    public ArrayList<RolDTO> obtenerRolesAdministrador() {
        return rolAdministradorRepository.findAll().stream()
            .map(rolMapper::toDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RolDTO> obtenerRolesConductor() {
        return rolConductorRepository.findAll().stream()
            .map(rolMapper::toDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<RolDTO> obtenerRolesDespachador() {
        return rolDespachadorRepository.findAll().stream()
            .map(rolMapper::toDTO)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<RolDTO> obtenerRolPorId(int id) {
        Optional<? extends Rol> rol = Optional.empty();
        if (rolAdministradorRepository.findById(id).isPresent()) {
            rol = rolAdministradorRepository.findById(id);
        } else if (rolConductorRepository.findById(id).isPresent()) {
            rol = rolConductorRepository.findById(id);
        } else if (rolDespachadorRepository.findById(id).isPresent()) {
            rol = rolDespachadorRepository.findById(id);
        }
        return rol.map(rolMapper::toDTO);
    }

    public RolDTO guardarRol(RolDTO rolDTO) {
        Rol rol = rolMapper.toEntity(rolDTO);
        if (rol instanceof RolAdministrador) { 
            return rolMapper.toDTO(rolAdministradorRepository.save((RolAdministrador) rol));
        } else if (rol instanceof RolConductor) {
            return rolMapper.toDTO(rolConductorRepository.save((RolConductor) rol));
        } else if (rol instanceof RolDespachador) {
            return rolMapper.toDTO(rolDespachadorRepository.save((RolDespachador) rol));
        }
        throw new IllegalArgumentException("Tipo de rol desconocido");
    }
}
