package com.translogistics.parcial.mapper;
import com.translogistics.parcial.dto.RolAdministradorDTO;
import com.translogistics.parcial.model.RolAdministrador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolAdministradorMapper {

      RolAdministradorDTO toDTO(RolAdministrador entity);
       RolAdministrador toEntity(RolAdministradorDTO dto);
    
}
