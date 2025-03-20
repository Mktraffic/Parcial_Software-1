package com.translogistics.parcial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.translogistics.parcial.model.Rol;
import com.translogistics.parcial.model.RolAdministrador;
import com.translogistics.parcial.model.RolConductor;
import com.translogistics.parcial.model.RolDespachador;
import com.translogistics.parcial.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public RolConductor guardarRolConductor(RolConductor rolConductor) {
        return rolRepository.save(rolConductor);
    }

    public RolDespachador guardarRolDespachador(RolDespachador rolDespachador) {
        return rolRepository.save(rolDespachador);
    }

    public RolAdministrador guardarRolAdministrador(RolAdministrador rolAdministrador) {
        return rolRepository.save(rolAdministrador);
    }
}
