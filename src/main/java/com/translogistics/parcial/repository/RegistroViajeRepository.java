package com.translogistics.parcial.repository;

import com.translogistics.parcial.model.RegistroViaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroViajeRepository extends JpaRepository<RegistroViaje, Integer>{

}
