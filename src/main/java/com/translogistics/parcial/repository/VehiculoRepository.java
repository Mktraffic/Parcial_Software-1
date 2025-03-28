package com.translogistics.parcial.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.translogistics.parcial.model.Vehiculo;



@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    
}