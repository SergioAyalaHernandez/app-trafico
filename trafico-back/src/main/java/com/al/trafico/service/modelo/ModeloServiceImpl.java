package com.al.trafico.service.modelo;

import com.al.trafico.dto.model.ModeloDTO;
import com.al.trafico.entity.Marca;
import com.al.trafico.entity.Modelo;
import com.al.trafico.repository.modelo.ModeloRepository;
import com.al.trafico.service.marca.MarcaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModeloServiceImpl implements ModeloService {

  private ModeloRepository modeloRepository;

  private MarcaService marcaService;

  @Override
  public Modelo guardar(ModeloDTO modeloDTO) {
    Marca marca = marcaService.obtenerPorId(modeloDTO.getMarcaId());
    Modelo modelo = new Modelo();
    modelo.setNombre(modeloDTO.getNombre());
    modelo.setPotencia(modeloDTO.getPotencia());
    modelo.setMarca(marca);
    return modeloRepository.save(modelo);
  }
  @Override
  public List<Modelo> listar() {
    return modeloRepository.findAll();
  }
  @Override
  public Optional<Modelo> buscarPorId(Long id) {
    return modeloRepository.findById(id);
  }
  @Override
  public void eliminar(Long id) {
    modeloRepository.deleteById(id);
  }

  @Override
  public Modelo actualizar(Long id, ModeloDTO modeloDTO) {
    Optional<Modelo> modeloOptional = modeloRepository.findById(id);
    if (modeloOptional.isPresent()) {
      Modelo modeloExistente = modeloOptional.get();
      Marca marca = marcaService.obtenerPorId(modeloDTO.getMarcaId());

      modeloExistente.setNombre(modeloDTO.getNombre());
      modeloExistente.setPotencia(modeloDTO.getPotencia());
      modeloExistente.setMarca(marca);

      return modeloRepository.save(modeloExistente);
    } else {
      throw new RuntimeException("Modelo no encontrado con ID: " + id);
    }
  }
}

