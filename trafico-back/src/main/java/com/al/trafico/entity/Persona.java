package com.al.trafico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Persona {

  @Id
  private String nit;

  private String nombre;

  private String apellidos;

  private LocalDate fechaNacimiento;

  private String calle;

  private String numero;

  private String municipio;

  private String provincia;

  private String codigoPostal;

  private String sexo;
}

