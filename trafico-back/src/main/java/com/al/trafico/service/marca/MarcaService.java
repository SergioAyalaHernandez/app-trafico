package com.al.trafico.service.marca;

import com.al.trafico.entity.Marca;

import java.util.List;

public interface MarcaService {
  Marca crear(Marca marca);
  Marca obtenerPorId(Long id);
  List<Marca> obtenerTodas();
  Marca actualizar(Long id, Marca marca);
  void eliminar(Long id);
}
