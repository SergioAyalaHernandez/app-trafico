package com.al.trafico.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Data
public class Infraccion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long numeroExpediente;

  @ManyToOne
  private Persona persona;

  @ManyToOne
  private Vehiculo vehiculo; // Puede ser null

  private LocalDate fecha;

  private String articuloInfringido;

  private String carretera;

  private String kilometro;

  private String direccion;

  private Double importe;

  private String estado;

  @ManyToOne
  private Agente agente;

}

