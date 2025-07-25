package com.al.trafico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Marca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  private String direccionSocial;

  @OneToMany(mappedBy = "marca")
  @JsonIgnore
  private List<Modelo> modelos;
}

