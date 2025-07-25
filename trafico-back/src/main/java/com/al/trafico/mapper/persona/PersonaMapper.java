package com.al.trafico.mapper.persona;

import org.mapstruct.Mapper;
import com.al.trafico.dto.persona.PersonaDto;
import com.al.trafico.entity.Persona;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
  PersonaDto toDto(Persona persona);
  Persona toEntity(PersonaDto personaDto);
}

