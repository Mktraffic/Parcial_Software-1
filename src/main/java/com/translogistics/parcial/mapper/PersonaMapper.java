package com.translogistics.parcial.mapper;

import com.translogistics.parcial.dto.PersonaDTO;
import com.translogistics.parcial.model.Persona;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaDTO toDTO(Persona persona);
    Persona toEntity(PersonaDTO personaDTO);
}
