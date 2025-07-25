package com.al.trafico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Vehiculo {
  @Id
  private String bastidor;

  private String matricula;

  private LocalDate fechaMatriculacion;

  @ManyToOne
  @JoinColumn(name = "modelo_id")
  private Modelo modelo;

  @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
  private List<PropiedadVehiculo> propietarios;
}

