package com.al.trafico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class PropiedadVehiculo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnore
  private Vehiculo vehiculo;

  @ManyToOne
  private Persona propietario;

  private LocalDate fechaInicio;

  private LocalDate fechaFin;

  private Boolean activo;
}

