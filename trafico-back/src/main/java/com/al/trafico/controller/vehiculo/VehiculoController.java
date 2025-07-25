package com.al.trafico.controller.vehiculo;

import com.al.trafico.dto.vehiculo.VehiculoDTO;
import com.al.trafico.entity.Vehiculo;
import com.al.trafico.service.vehiculo.VehiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/vehiculos")
public class VehiculoController {

  private final VehiculoService vehiculoService;

  @PostMapping
  public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
    if (vehiculoDTO.getPropietarioId() != null && !vehiculoService.existePropietario(vehiculoDTO.getPropietarioId())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    Vehiculo nuevoVehiculo = vehiculoService.guardarVehiculo(vehiculoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVehiculo);
  }

  @GetMapping
  public ResponseEntity<List<Vehiculo>> obtenerTodosLosVehiculos() {
    List<Vehiculo> vehiculos = vehiculoService.obtenerTodosLosVehiculos();
    return ResponseEntity.ok(vehiculos);
  }

  @GetMapping("/{bastidor}")
  public ResponseEntity<Vehiculo> obtenerVehiculoPorBastidor(@PathVariable String bastidor) {
    Optional<Vehiculo> vehiculo = vehiculoService.obtenerVehiculoPorBastidor(bastidor);
    return vehiculo.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/matricula/{matricula}")
  public ResponseEntity<Vehiculo> obtenerVehiculoPorMatricula(@PathVariable String matricula) {
    Optional<Vehiculo> vehiculo = vehiculoService.obtenerVehiculoPorMatricula(matricula);
    return vehiculo.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{bastidor}")
  public ResponseEntity<Void> eliminarVehiculo(@PathVariable String bastidor) {
    vehiculoService.eliminarVehiculo(bastidor);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/verificar-propietario/{propietarioId}")
  public ResponseEntity<Boolean> verificarPropietario(@PathVariable String propietarioId) {
    boolean existe = vehiculoService.existePropietario(propietarioId);
    return ResponseEntity.ok(existe);
  }


  @PostMapping("/{bastidor}/propietarios/{propietarioId}")
  public ResponseEntity<Vehiculo> agregarPropietario(
          @PathVariable String bastidor,
          @PathVariable String propietarioId) {
    try {
      Vehiculo vehiculoActualizado = vehiculoService.addPropietario(bastidor, propietarioId);
      return new ResponseEntity<>(vehiculoActualizado, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
