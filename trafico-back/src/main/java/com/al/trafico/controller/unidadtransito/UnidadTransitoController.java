package com.al.trafico.controller.unidadtransito;

import com.al.trafico.entity.UnidadTransito;
import com.al.trafico.service.unidadtransito.UnidadTransitoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/unidades")
public class UnidadTransitoController {

  private final UnidadTransitoService service;

  @PostMapping
  public UnidadTransito crear(@RequestBody UnidadTransito unidad) {
    return service.guardar(unidad);
  }

  @GetMapping
  public List<UnidadTransito> listar() {
    return service.listar();
  }

  @GetMapping("/{id}")
  public UnidadTransito obtener(@PathVariable Long id) {
    return service.buscarPorId(id);
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
