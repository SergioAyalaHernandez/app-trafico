package com.al.trafico.service.persona;

import com.al.trafico.dto.persona.PersonaDto;

import java.util.List;

public interface PersonaService {
  PersonaDto crearPersona(PersonaDto personaDto);
  PersonaDto obtenerPersonaPorNit(String nit);
  List<PersonaDto> listarPersonas();
  PersonaDto actualizarPersona(String nit, PersonaDto personaDto);
  void eliminarPersona(String nit);
}
