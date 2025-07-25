package com.al.trafico.controller.infraccion;

import com.al.trafico.dto.infraccion.InfraccionDTO;
import com.al.trafico.entity.Infraccion;
import com.al.trafico.service.infraccion.InfraccionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/infracciones")
public class InfraccionController {

  private InfraccionService infraccionService;

  @PostMapping
  public Infraccion crear(@RequestBody InfraccionDTO dto) {
    return infraccionService.crearInfraccion(dto);
  }

  @GetMapping("/{numeroExpediente}")
  public Infraccion obtener(@PathVariable Long numeroExpediente) {
    return infraccionService.obtenerInfraccion(numeroExpediente);
  }

  @GetMapping
  public List<Infraccion> listar() {
    return infraccionService.listarInfracciones();
  }

  @PutMapping("/{numeroExpediente}")
  public Infraccion actualizar(@PathVariable Long numeroExpediente, @RequestBody InfraccionDTO dto) {
    return infraccionService.actualizarInfraccion(numeroExpediente, dto);
  }

  @DeleteMapping("/{numeroExpediente}")
  public void eliminar(@PathVariable Long numeroExpediente) {
    infraccionService.eliminarInfraccion(numeroExpediente);
  }
}
