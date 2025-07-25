package com.al.trafico.service.informes;

import com.al.trafico.projections.*;
import com.al.trafico.repository.infraccion.InfraccionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformesServiceImpl implements InformesService {

  private final InfraccionRepository infraccionRepository;

  @Autowired
  public InformesServiceImpl(InfraccionRepository infraccionRepository) {
    this.infraccionRepository = infraccionRepository;
  }

  @Override
  public List<InfraccionPorCarretera> getInfraccionesPorCarreteraSemanActual() {
    return infraccionRepository.countInfraccionesPorCarreteraSemanActual();
  }

  @Override
  public List<InfraccionPorImporte> getInfraccionesPorImporteSemanActual() {
    return infraccionRepository.countInfraccionesPorImporteSemanActual();
  }

  @Override
  public List<String> getArticulosInfringidosSemanActual() {
    return infraccionRepository.findArticulosInfringidosSemanActual();
  }

  @Override
  public List<InfraccionConAgente> getInfraccionesByUnidadId(Long unidadId) {
    return infraccionRepository.findInfraccionesByUnidadId(unidadId);
  }

  @Override
  public List<DemografiaInfractor> getDemografiaInfractores() {
    return infraccionRepository.getDemografiaInfractores();
  }

  @Override
  public List<InfraccionPorEdadSexo> getInfraccionesPorEdadYSexo() {
    return infraccionRepository.getInfraccionesPorEdadYSexo();
  }

  @Override
  public List<InfraccionPorVehiculo> getInfraccionesPorMarcaYModelo() {
    return infraccionRepository.getInfraccionesPorMarcaYModelo();
  }
}
