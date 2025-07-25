package com.al.trafico.service.unidadtransito;

import com.al.trafico.entity.UnidadTransito;
import com.al.trafico.repository.unidadtransito.UnidadTransitoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnidadTransitoServiceImpl implements UnidadTransitoService {

  private final UnidadTransitoRepository repository;

  @Override
  public UnidadTransito guardar(UnidadTransito unidad) {
    return repository.save(unidad);
  }

  @Override
  public List<UnidadTransito> listar() {
    return repository.findAll();
  }

  @Override
  public UnidadTransito buscarPorId(Long id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
