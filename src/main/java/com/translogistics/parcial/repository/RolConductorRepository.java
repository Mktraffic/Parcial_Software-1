package com.translogistics.parcial.repository;


import com.translogistics.parcial.model.RolConductor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolConductorRepository extends JpaRepository<RolConductor, Integer> {

}

