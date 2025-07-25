package com.al.trafico.service.unidadtransito;

import com.al.trafico.entity.UnidadTransito;
import java.util.List;

public interface UnidadTransitoService {
  UnidadTransito guardar(UnidadTransito unidad);
  List<UnidadTransito> listar();
  UnidadTransito buscarPorId(Long id);
  void eliminar(Long id);
}