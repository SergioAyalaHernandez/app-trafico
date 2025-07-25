package com.al.trafico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Agente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "persona_id")
  private Persona persona;

  @ManyToOne
  private UnidadTransito unidad;
}

