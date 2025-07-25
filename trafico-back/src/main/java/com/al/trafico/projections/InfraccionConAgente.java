package com.al.trafico.projections;

public interface InfraccionConAgente {
  Long getNumeroExpediente();
  String getCarretera();
  Double getImporte();
  String getArticuloInfringido();
  String getNombreAgente();
}
