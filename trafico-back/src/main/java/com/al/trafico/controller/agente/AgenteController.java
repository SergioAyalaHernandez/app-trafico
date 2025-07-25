package com.al.trafico.controller.agente;

import com.al.trafico.dto.agente.AgenteDTO;
import com.al.trafico.entity.Agente;
import com.al.trafico.service.agente.AgenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/agentes")
public class AgenteController {

  private final AgenteService agenteService;


  @PostMapping
  public ResponseEntity<Agente> crearAgente(@RequestBody AgenteDTO agenteDTO) {
    Agente nuevoAgente = agenteService.crearAgente(agenteDTO);
    return new ResponseEntity<>(nuevoAgente, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarAgente(@PathVariable Long id) {
    agenteService.eliminarAgente(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Agente> obtenerAgente(@PathVariable Long id) {
    return agenteService.buscarPorId(id)
            .map(agente -> new ResponseEntity<>(agente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public ResponseEntity<List<Agente>> listarAgentes() {
    List<Agente> agentes = agenteService.listarTodos();
    return new ResponseEntity<>(agentes, HttpStatus.OK);
  }

  @PatchMapping("/{id}/unidad/{unidadTransitoId}")
  public ResponseEntity<Agente> actualizarUnidadTransito(
          @PathVariable Long id,
          @PathVariable Long unidadTransitoId) {
    Agente agenteActualizado = agenteService.actualizarUnidadTransito(id, unidadTransitoId);
    return new ResponseEntity<>(agenteActualizado, HttpStatus.OK);
  }
}