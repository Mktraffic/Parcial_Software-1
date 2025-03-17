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
