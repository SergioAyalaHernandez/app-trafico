package com.al.trafico.service.modelo;

import com.al.trafico.dto.model.ModeloDTO;
import com.al.trafico.entity.Modelo;

import java.util.List;
import java.util.Optional;

public interface ModeloService {
  Modelo guardar(ModeloDTO modeloDTO);

  List<Modelo> listar();

  Optional<Modelo> buscarPorId(Long id);

  void eliminar(Long id);

  Modelo actualizar(Long id, ModeloDTO modeloDTO);
}