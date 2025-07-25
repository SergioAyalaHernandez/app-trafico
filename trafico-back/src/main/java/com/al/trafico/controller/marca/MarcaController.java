package com.al.trafico.controller.marca;

import com.al.trafico.entity.Marca;
import com.al.trafico.service.marca.MarcaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
@AllArgsConstructor
@CrossOrigin("*")
public class MarcaController {

  private MarcaService marcaService;

  @PostMapping
  public Marca crear(@RequestBody Marca marca) {
    return marcaService.crear(marca);
  }

  @GetMapping("/{id}")
  public Marca obtenerPorId(@PathVariable Long id) {
    return marcaService.obtenerPorId(id);
  }

  @GetMapping
  public List<Marca> obtenerTodas() {
    return marcaService.obtenerTodas();
  }

  @PutMapping("/{id}")
  public Marca actualizar(@PathVariable Long id, @RequestBody Marca marca) {
    return marcaService.actualizar(id, marca);
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable Long id) {
    marcaService.eliminar(id);
  }
}
