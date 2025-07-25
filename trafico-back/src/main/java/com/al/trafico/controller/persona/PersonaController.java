package com.al.trafico.controller.persona;

import com.al.trafico.dto.persona.PersonaDto;
import com.al.trafico.service.persona.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PersonaController {

  private final PersonaService personaService;

  @PostMapping
  public ResponseEntity<PersonaDto> crear(@RequestBody PersonaDto personaDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(personaService.crearPersona(personaDto));
  }

  @GetMapping("/{nit}")
  public ResponseEntity<PersonaDto> obtener(@PathVariable String nit) {
    return ResponseEntity.ok(personaService.obtenerPersonaPorNit(nit));
  }

  @GetMapping
  public ResponseEntity<List<PersonaDto>> listar() {
    return ResponseEntity.ok(personaService.listarPersonas());
  }

  @PutMapping("/{nit}")
  public ResponseEntity<PersonaDto> actualizar(@PathVariable String nit, @RequestBody PersonaDto personaDto) {
    return ResponseEntity.ok(personaService.actualizarPersona(nit, personaDto));
  }

  @DeleteMapping("/{nit}")
  public ResponseEntity<Void> eliminar(@PathVariable String nit) {
    personaService.eliminarPersona(nit);
    return ResponseEntity.noContent().build();
  }
}

