package com.al.trafico.service.vehiculo;


import com.al.trafico.dto.vehiculo.VehiculoDTO;
import com.al.trafico.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
  Vehiculo guardarVehiculo(VehiculoDTO vehiculoDTO);
  List<Vehiculo> obtenerTodosLosVehiculos();
  Optional<Vehiculo> obtenerVehiculoPorBastidor(String bastidor);
  Optional<Vehiculo> obtenerVehiculoPorMatricula(String matricula);
  void eliminarVehiculo(String bastidor);
  boolean existePropietario(String propietarioId);
  Vehiculo addPropietario(String bastidor, String propietarioId);
}
