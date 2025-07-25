package com.al.trafico.service.informes;

import com.al.trafico.projections.*;

import java.util.List;

public interface InformesService {
  List<InfraccionPorCarretera> getInfraccionesPorCarreteraSemanActual();
  List<InfraccionPorImporte> getInfraccionesPorImporteSemanActual();
  List<String> getArticulosInfringidosSemanActual();
  List<InfraccionConAgente> getInfraccionesByUnidadId(Long unidadId);
  List<DemografiaInfractor> getDemografiaInfractores();
  List<InfraccionPorEdadSexo> getInfraccionesPorEdadYSexo();
  List<InfraccionPorVehiculo> getInfraccionesPorMarcaYModelo();
}
