package com.al.trafico.service.infraccion;

import com.al.trafico.dto.infraccion.InfraccionDTO;
import com.al.trafico.entity.Infraccion;
import com.al.trafico.repository.agente.AgenteRepository;
import com.al.trafico.repository.infraccion.InfraccionRepository;
import com.al.trafico.repository.persona.PersonaRepository;
import com.al.trafico.repository.vehiculo.VehiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InfraccionServiceImpl implements InfraccionService {


  private InfraccionRepository infraccionRepository;

  private AgenteRepository agenteRepository;

  private PersonaRepository personaRepository;

  private VehiculoRepository vehiculoRepository;

  @Override
  public Infraccion crearInfraccion(InfraccionDTO dto) {
    Infraccion infraccion = new Infraccion();
    infraccion.setAgente(agenteRepository.findById(dto.getAgenteId()).orElse(null));
    infraccion.setPersona(personaRepository.findById(dto.getPersonaId()).orElse(null));
    if (dto.getVehiculoId() != null) {
      infraccion.setVehiculo(vehiculoRepository.findById(dto.getVehiculoId()).orElse(null));
    }
    infraccion.setFecha(dto.getFecha());
    infraccion.setArticuloInfringido(dto.getArticuloInfringido());
    infraccion.setCarretera(dto.getCarretera());
    infraccion.setKilometro(dto.getKilometro());
    infraccion.setDireccion(dto.getDireccion());
    infraccion.setImporte(dto.getImporte());
    infraccion.setEstado(dto.getEstado());
    return infraccionRepository.save(infraccion);
  }

  @Override
  public Infraccion obtenerInfraccion(Long numeroExpediente) {
    return infraccionRepository.findById(numeroExpediente).orElse(null);
  }

  @Override
  public List<Infraccion> listarInfracciones() {
    return infraccionRepository.findAll();
  }

  @Override
  public Infraccion actualizarInfraccion(Long numeroExpediente, InfraccionDTO dto) {
    Infraccion infraccion = infraccionRepository.findById(numeroExpediente).orElse(null);
    if (infraccion == null) return null;
    infraccion.setAgente(agenteRepository.findById(dto.getAgenteId()).orElse(null));
    infraccion.setPersona(personaRepository.findById(dto.getPersonaId()).orElse(null));
    if (dto.getVehiculoId() != null) {
      infraccion.setVehiculo(vehiculoRepository.findById(dto.getVehiculoId()).orElse(null));
    } else {
      infraccion.setVehiculo(null);
    }
    infraccion.setFecha(dto.getFecha());
    infraccion.setArticuloInfringido(dto.getArticuloInfringido());
    infraccion.setCarretera(dto.getCarretera());
    infraccion.setKilometro(dto.getKilometro());
    infraccion.setDireccion(dto.getDireccion());
    infraccion.setImporte(dto.getImporte());
    infraccion.setEstado(dto.getEstado());
    return infraccionRepository.save(infraccion);
  }

  @Override
  public void eliminarInfraccion(Long numeroExpediente) {
    infraccionRepository.deleteById(numeroExpediente);
  }
}