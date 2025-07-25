package com.al.trafico.service.agente;

import com.al.trafico.dto.agente.AgenteDTO;
import com.al.trafico.entity.Agente;
import com.al.trafico.entity.Persona;
import com.al.trafico.entity.UnidadTransito;
import com.al.trafico.repository.agente.AgenteRepository;
import com.al.trafico.repository.persona.PersonaRepository;
import com.al.trafico.repository.unidadtransito.UnidadTransitoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteServiceImpl implements AgenteService {

  private final AgenteRepository agenteRepository;
  private final PersonaRepository personaRepository;
  private final UnidadTransitoRepository unidadTransitoRepository;

  @Autowired
  public AgenteServiceImpl(AgenteRepository agenteRepository,
                           PersonaRepository personaRepository,
                           UnidadTransitoRepository unidadTransitoRepository) {
    this.agenteRepository = agenteRepository;
    this.personaRepository = personaRepository;
    this.unidadTransitoRepository = unidadTransitoRepository;
  }

  @Override
  @Transactional
  public Agente crearAgente(AgenteDTO agenteDTO) {
    Persona persona = personaRepository.findById(agenteDTO.getPersonaId())
            .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + agenteDTO.getPersonaId()));

    UnidadTransito unidadTransito = unidadTransitoRepository.findById(agenteDTO.getUnidadTransitoId())
            .orElseThrow(() -> new EntityNotFoundException("Unidad de tránsito no encontrada con ID: " + agenteDTO.getUnidadTransitoId()));

    Agente agente = new Agente();
    agente.setPersona(persona);
    agente.setUnidad(unidadTransito);

    return agenteRepository.save(agente);
  }

  @Override
  @Transactional
  public void eliminarAgente(Long id) {
    agenteRepository.deleteById(id);
  }

  @Override
  public Optional<Agente> buscarPorId(Long id) {
    return agenteRepository.findById(id);
  }

  @Override
  public List<Agente> listarTodos() {
    return agenteRepository.findAll();
  }

  @Override
  @Transactional
  public Agente actualizarUnidadTransito(Long id, Long unidadTransitoId) {
    Agente agente = agenteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agente no encontrado con ID: " + id));

    UnidadTransito unidadTransito = unidadTransitoRepository.findById(unidadTransitoId)
            .orElseThrow(() -> new EntityNotFoundException("Unidad de tránsito no encontrada con ID: " + unidadTransitoId));

    agente.setUnidad(unidadTransito);
    return agenteRepository.save(agente);
  }
}
