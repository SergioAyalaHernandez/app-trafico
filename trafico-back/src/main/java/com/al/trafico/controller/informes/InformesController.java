package com.al.trafico.controller.informes;


import com.al.trafico.projections.*;
import com.al.trafico.service.informes.InformesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/informes")
public class InformesController {

  private final InformesService informesService;

  @Autowired
  public InformesController(InformesService informesService) {
    this.informesService = informesService;
  }

  @GetMapping("/infracciones-por-carretera")
  public ResponseEntity<List<InfraccionPorCarretera>> getInfraccionesPorCarreteraSemanActual() {
    return ResponseEntity.ok(informesService.getInfraccionesPorCarreteraSemanActual());
  }

  @GetMapping("/infracciones-por-importe")
  public ResponseEntity<List<InfraccionPorImporte>> getInfraccionesPorImporteSemanActual() {
    return ResponseEntity.ok(informesService.getInfraccionesPorImporteSemanActual());
  }

  @GetMapping("/articulos-infringidos")
  public ResponseEntity<List<String>> getArticulosInfringidosSemanActual() {
    return ResponseEntity.ok(informesService.getArticulosInfringidosSemanActual());
  }

  @GetMapping("/infracciones-por-unidad/{unidadId}")
  public ResponseEntity<List<InfraccionConAgente>> getInfraccionesByUnidadId(@PathVariable Long unidadId) {
    return ResponseEntity.ok(informesService.getInfraccionesByUnidadId(unidadId));
  }

  @GetMapping("/demografia-infractores")
  public ResponseEntity<List<DemografiaInfractor>> getDemografiaInfractores() {
    return ResponseEntity.ok(informesService.getDemografiaInfractores());
  }

  @GetMapping("/infracciones-por-edad-sexo")
  public ResponseEntity<List<InfraccionPorEdadSexo>> getInfraccionesPorEdadYSexo() {
    return ResponseEntity.ok(informesService.getInfraccionesPorEdadYSexo());
  }

  @GetMapping("/infracciones-por-vehiculo")
  public ResponseEntity<List<InfraccionPorVehiculo>> getInfraccionesPorMarcaYModelo() {
    return ResponseEntity.ok(informesService.getInfraccionesPorMarcaYModelo());
  }
}