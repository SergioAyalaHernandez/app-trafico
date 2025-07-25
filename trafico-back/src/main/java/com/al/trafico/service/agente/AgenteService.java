package com.al.trafico.service.agente;

import com.al.trafico.dto.agente.AgenteDTO;
import com.al.trafico.entity.Agente;

import java.util.List;
import java.util.Optional;

public interface AgenteService {
  Agente crearAgente(AgenteDTO agenteDTO);
  void eliminarAgente(Long id);
  Optional<Agente> buscarPorId(Long id);
  List<Agente> listarTodos();
  Agente actualizarUnidadTransito(Long id, Long unidadTransitoId);
}
