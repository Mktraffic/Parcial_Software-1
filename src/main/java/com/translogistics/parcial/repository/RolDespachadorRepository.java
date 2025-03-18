package com.translogistics.parcial.repository;

import com.translogistics.parcial.model.RolDespachador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolDespachadorRepository extends JpaRepository<RolDespachador, Long> {

}

