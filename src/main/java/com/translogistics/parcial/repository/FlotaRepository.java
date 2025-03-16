package com.translogistics.parcial.repository;

import com.translogistics.parcial.model.Flota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlotaRepository extends JpaRepository<Flota, Integer> {
}