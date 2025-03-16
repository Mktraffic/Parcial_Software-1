package com.translogistics.parcial.repository;

import com.translogistics.parcial.model.PlanillaViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaViajeRepository extends JpaRepository<PlanillaViaje, Integer>{

}
