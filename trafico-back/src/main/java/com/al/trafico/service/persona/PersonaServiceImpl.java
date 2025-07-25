package com.al.trafico.service.persona;

import com.al.trafico.dto.persona.PersonaDto;
import com.al.trafico.entity.Persona;
import com.al.trafico.mapper.persona.PersonaMapper;
import com.al.trafico.repository.persona.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

  private final PersonaRepository personaRepository;
  private final PersonaMapper personaMapper;

  @Override
  public PersonaDto crearPersona(PersonaDto personaDto) {
    Persona persona = personaMapper.toEntity(personaDto);
    return personaMapper.toDto(personaRepository.save(persona));
  }

  @Override
  public PersonaDto obtenerPersonaPorNit(String nit) {
    Persona persona = personaRepository.findById(nit)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con NIT: " + nit));
    return personaMapper.toDto(persona);
  }

  @Override
  public List<PersonaDto> listarPersonas() {
    return personaRepository.findAll().stream()
            .map(personaMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public PersonaDto actualizarPersona(String nit, PersonaDto personaDto) {
    Persona existente = personaRepository.findById(nit)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con NIT: " + nit));
    Persona actualizada = personaMapper.toEntity(personaDto);
    actualizada.setNit(nit);
    return personaMapper.toDto(personaRepository.save(actualizada));
  }

  @Override
  public void eliminarPersona(String nit) {
    if (!personaRepository.existsById(nit)) {
      throw new RuntimeException("Persona no encontrada con NIT: " + nit);
    }
    personaRepository.deleteById(nit);
  }
}

