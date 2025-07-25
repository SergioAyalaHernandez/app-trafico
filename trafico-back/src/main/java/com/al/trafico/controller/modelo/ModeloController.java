package com.al.trafico.controller.modelo;

import com.al.trafico.dto.model.ModeloDTO;
import com.al.trafico.entity.Modelo;
import com.al.trafico.service.modelo.ModeloServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/modelos")
@CrossOrigin("*")
public class ModeloController {

  private ModeloServiceImpl modeloService;

  @PostMapping
  public ResponseEntity<Modelo> crear(@RequestBody ModeloDTO modeloDTO) {
    return ResponseEntity.ok(modeloService.guardar(modeloDTO));
  }

  @GetMapping
  public ResponseEntity<List<Modelo>> listar() {
    return ResponseEntity.ok(modeloService.listar());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Modelo> obtener(@PathVariable Long id) {
    return modeloService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    modeloService.eliminar(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Modelo> actualizarModelo(@PathVariable Long id, @RequestBody ModeloDTO modeloDTO) {
    try {
      Modelo modeloActualizado = modeloService.actualizar(id, modeloDTO);
      return ResponseEntity.ok(modeloActualizado);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
