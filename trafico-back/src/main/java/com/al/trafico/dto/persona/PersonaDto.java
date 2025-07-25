package com.al.trafico.dto.persona;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaDto {
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

