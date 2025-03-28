package com.translogistics.parcial.service;

import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.translogistics.parcial.dto.RegistroViajeDTO;
import com.translogistics.parcial.mapper.RegistroViajeMapper;
import com.translogistics.parcial.model.RegistroViaje;
import com.translogistics.parcial.repository.RegistroViajeRepository;

@Service
public class RegistroViajeService {

    @Autowired
    private RegistroViajeMapper registroViajeMapper;
    @Autowired
    private RegistroViajeRepository registroViajeRepository;

    public List<RegistroViajeDTO> findAllRegistroViaje(){
        List<RegistroViaje> registroViajes = registroViajeRepository.findAll();
        return registroViajes.stream().map(registroViajeMapper::toDTO).collect(Collectors.toList());
    }

    public RegistroViajeDTO addRegistroViaje(RegistroViajeDTO registroViajeDTO){
        RegistroViaje savedRegistroViaje = registroViajeRepository.save(registroViajeMapper.toEntity(registroViajeDTO));
        return registroViajeMapper.toDTO(savedRegistroViaje);
    }

    
}