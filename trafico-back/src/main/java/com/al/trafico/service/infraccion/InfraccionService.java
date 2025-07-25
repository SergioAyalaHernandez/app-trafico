package com.al.trafico.service.infraccion;


import com.al.trafico.dto.infraccion.InfraccionDTO;
import com.al.trafico.entity.Infraccion;

import java.util.List;

public interface InfraccionService {
  Infraccion crearInfraccion(InfraccionDTO dto);
  Infraccion obtenerInfraccion(Long numeroExpediente);
  List<Infraccion> listarInfracciones();
  Infraccion actualizarInfraccion(Long numeroExpediente, InfraccionDTO dto);
  void eliminarInfraccion(Long numeroExpediente);
}
