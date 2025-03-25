package com.translogistics.parcial.repository;

import com.translogistics.parcial.model.RolAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolAdministradorRepository extends JpaRepository<RolAdministrador, Long> {

}
