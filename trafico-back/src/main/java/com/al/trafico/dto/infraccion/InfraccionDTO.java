package com.al.trafico.dto.infraccion;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InfraccionDTO {
  private Long agenteId;
  private String personaId;
  private String vehiculoId; // Puede ser null
  private LocalDate fecha;
  private String articuloInfringido;
  private String carretera;
  private String kilometro;
  private String direccion;
  private Double importe;
  private String estado;
}