package com.al.trafico.service.vehiculo;

import com.al.trafico.dto.vehiculo.VehiculoDTO;
import com.al.trafico.entity.Modelo;
import com.al.trafico.entity.Persona;
import com.al.trafico.entity.PropiedadVehiculo;
import com.al.trafico.entity.Vehiculo;
import com.al.trafico.repository.modelo.ModeloRepository;
import com.al.trafico.repository.persona.PersonaRepository;
import com.al.trafico.repository.vehiculo.VehiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

  private final VehiculoRepository vehiculoRepository;
  private final ModeloRepository modeloRepository;
  private final PersonaRepository propietarioRepository;


  @Override
  public Vehiculo guardarVehiculo(VehiculoDTO vehiculoDTO) {
    Vehiculo vehiculo = new Vehiculo();
    vehiculo.setBastidor(vehiculoDTO.getBastidor());
    vehiculo.setMatricula(vehiculoDTO.getMatricula().toUpperCase());
    vehiculo.setFechaMatriculacion(vehiculoDTO.getFechaMatriculacion());
    // Asignar modelo
    Optional<Modelo> modeloOpt = modeloRepository.findById(vehiculoDTO.getModeloId());
    modeloOpt.ifPresent(vehiculo::setModelo);

    // Verificar si existe el propietario
    if (vehiculoDTO.getPropietarioId() != null && existePropietario(vehiculoDTO.getPropietarioId())) {
      PropiedadVehiculo propiedadVehiculo = new PropiedadVehiculo();
      propiedadVehiculo.setVehiculo(vehiculo);
      propiedadVehiculo.setFechaInicio(LocalDate.now());
      propiedadVehiculo.setActivo(true);

      Persona propietario = propietarioRepository.findById(vehiculoDTO.getPropietarioId())
              .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
      propiedadVehiculo.setPropietario(propietario);

      List<PropiedadVehiculo> propiedades = new ArrayList<>();
      propiedades.add(propiedadVehiculo);
      vehiculo.setPropietarios(propiedades);
    }

    return vehiculoRepository.save(vehiculo);
  }

  @Override
  public List<Vehiculo> obtenerTodosLosVehiculos() {
    return vehiculoRepository.findAll().stream()
            .filter(vehiculo -> {
              List<PropiedadVehiculo> propietarios = vehiculo.getPropietarios();
              return propietarios != null &&
                      propietarios.stream().anyMatch(PropiedadVehiculo::getActivo);
            })
            .map(vehiculo -> {
              // Crear copia del vehículo para no modificar la entidad en caché
              Vehiculo vehiculoFiltrado = new Vehiculo();
              vehiculoFiltrado.setBastidor(vehiculo.getBastidor());
              vehiculoFiltrado.setMatricula(vehiculo.getMatricula());
              vehiculoFiltrado.setFechaMatriculacion(vehiculo.getFechaMatriculacion());
              vehiculoFiltrado.setModelo(vehiculo.getModelo());

              // Filtrar solo propietarios activos
              List<PropiedadVehiculo> propietariosActivos = vehiculo.getPropietarios().stream()
                      .filter(PropiedadVehiculo::getActivo)
                      .collect(Collectors.toList());
              vehiculoFiltrado.setPropietarios(propietariosActivos);

              return vehiculoFiltrado;
            })
            .collect(Collectors.toList());
  }

  @Override
  public Optional<Vehiculo> obtenerVehiculoPorBastidor(String bastidor) {
    return vehiculoRepository.findById(bastidor);
  }

  @Override
  public Optional<Vehiculo> obtenerVehiculoPorMatricula(String matricula) {
    return vehiculoRepository.findByMatricula(matricula);
  }

  @Override
  public void eliminarVehiculo(String bastidor) {
    vehiculoRepository.deleteById(bastidor);
  }

  @Override
  public boolean existePropietario(String propietarioId) {
    return propietarioRepository.existsById(propietarioId);
  }

  @Override
  public Vehiculo addPropietario(String bastidor, String propietarioId) {
    Vehiculo vehiculoExistente = vehiculoRepository.findById(bastidor)
            .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con bastidor: " + bastidor));

    Persona personaNueva = propietarioRepository.findById(propietarioId).orElseThrow(
            () -> new RuntimeException("Propietario no encontrado con ID: " + propietarioId));

    List<PropiedadVehiculo> propiedades = vehiculoExistente.getPropietarios();

    LocalDate hoy = LocalDate.now();
    for (PropiedadVehiculo propiedad : propiedades) {
      if (propiedad.getFechaFin() == null) {
        propiedad.setFechaFin(hoy);
      }
      propiedad.setActivo(false);
    }
    PropiedadVehiculo nuevaPropiedad = new PropiedadVehiculo();
    nuevaPropiedad.setVehiculo(vehiculoExistente);
    nuevaPropiedad.setPropietario(personaNueva);
    nuevaPropiedad.setFechaInicio(hoy);
    nuevaPropiedad.setActivo(true);
    propiedades.add(nuevaPropiedad);

    return vehiculoRepository.save(vehiculoExistente);
  }
}
