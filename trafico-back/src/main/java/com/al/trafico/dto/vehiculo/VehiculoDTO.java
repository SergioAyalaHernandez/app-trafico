package com.al.trafico.dto.vehiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
  private String bastidor;
  private String matricula;
  private LocalDate fechaMatriculacion;
  private Long modeloId;
  private String propietarioId;
}