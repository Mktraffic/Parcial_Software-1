package com.translogistics.parcial.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.translogistics.parcial.mapper.PlanillaViajeMapper;
import com.translogistics.parcial.repository.PlanillaViajeRepository;
import com.translogistics.parcial.model.PlanillaViaje;
import com.translogistics.parcial.dto.PlanillaViajeDTO;


@Service
public class PlanillaViajeService {
    @Autowired
    private PlanillaViajeMapper planillaViajeMapper;
    @Autowired
    private PlanillaViajeRepository planillaViajeRepository;

    public List<PlanillaViajeDTO> findAllPlanillaViaje(){
        List<PlanillaViaje> planillaViaje = planillaViajeRepository.findAll();
        return planillaViaje.stream().map(planillaViajeMapper::toDTO).collect(Collectors.toList());
    }
    public PlanillaViajeDTO addPlanillaViaje(PlanillaViajeDTO planillaViajeDTO){
        PlanillaViaje savedPlanillaViaje = planillaViajeRepository.save(planillaViajeMapper.toEntity(planillaViajeDTO));
        return planillaViajeMapper.toDTO(savedPlanillaViaje);
    }
    public ResponseEntity<?> searchPlanillaViaje(int id){
        Optional<PlanillaViaje> planilla = planillaViajeRepository.findById(id);
        if (planilla.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(planillaViajeMapper.toDTO(planilla.get()),HttpStatus.OK);
    }

}