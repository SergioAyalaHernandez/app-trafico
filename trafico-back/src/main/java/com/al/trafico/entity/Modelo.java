package com.al.trafico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Modelo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  private String potencia;

  @ManyToOne
  @JoinColumn(name = "marca_id")
  private Marca marca;
}

