package com.al.trafico.service.marca;

import com.al.trafico.entity.Marca;
import com.al.trafico.repository.marca.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {

  @Autowired
  private MarcaRepository marcaRepository;

  @Override
  public Marca crear(Marca marca) {
    return marcaRepository.save(marca);
  }

  @Override
  public Marca obtenerPorId(Long id) {
    return marcaRepository.findById(id).orElse(null);
  }

  @Override
  public List<Marca> obtenerTodas() {
    return marcaRepository.findAll();
  }

  @Override
  public Marca actualizar(Long id, Marca marca) {
    Optional<Marca> marcaExistente = marcaRepository.findById(id);
    if (marcaExistente.isPresent()) {
      Marca m = marcaExistente.get();
      m.setNombre(marca.getNombre());
      m.setDireccionSocial(marca.getDireccionSocial());
      return marcaRepository.save(m);
    }
    return null;
  }

  @Override
  public void eliminar(Long id) {
    marcaRepository.deleteById(id);
  }
}
