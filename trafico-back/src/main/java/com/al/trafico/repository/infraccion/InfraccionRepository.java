package com.al.trafico.repository.infraccion;

import com.al.trafico.entity.Infraccion;
import com.al.trafico.projections.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InfraccionRepository extends JpaRepository<Infraccion, Long> {
  @Query(value = "SELECT i.carretera as carretera, COUNT(*) AS cantidadInfracciones " +
          "FROM infraccion i " +
          "WHERE i.fecha >= DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())) DAY) " +
          "AND i.fecha < DATE_ADD(DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())) DAY), INTERVAL 7 DAY) " +
          "GROUP BY i.carretera", nativeQuery = true)
  List<InfraccionPorCarretera> countInfraccionesPorCarreteraSemanActual();

  // 2. Infracciones por importe en la semana actual
  @Query(value = "SELECT i.importe as importe, COUNT(*) AS cantidadInfracciones " +
          "FROM infraccion i " +
          "WHERE i.fecha >= DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())) DAY) " +
          "AND i.fecha < DATE_ADD(DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())) DAY), INTERVAL 7 DAY) " +
          "GROUP BY i.importe", nativeQuery = true)
  List<InfraccionPorImporte> countInfraccionesPorImporteSemanActual();

  // 3. Artículos infringidos en la semana actual
  @Query(value = "SELECT i.articulo_infringido FROM infraccion i " +
          "WHERE i.fecha >= DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())) DAY) " +
          "AND i.fecha < DATE_ADD(DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())) DAY), INTERVAL 7 DAY)", nativeQuery = true)
  List<String> findArticulosInfringidosSemanActual();

  // 4. Infracciones con nombre de agente por unidad
  @Query(value = "SELECT i.numero_expediente, i.carretera, i.importe, i.articulo_infringido, " +
          "CONCAT(p.nombre, ' ', p.apellidos) as nombreAgente " +
          "FROM infraccion i " +
          "LEFT JOIN agente a ON i.agente_id = a.id " +
          "LEFT JOIN persona p ON a.persona_id = p.nit " +
          "WHERE a.unidad_id = :unidadId", nativeQuery = true)
  List<InfraccionConAgente> findInfraccionesByUnidadId(@Param("unidadId") Long unidadId);

  // 5. Demografía de infractores
  @Query(value = "SELECT p1.sexo AS sexoInfractor, " +
          "TIMESTAMPDIFF(YEAR, p1.fecha_nacimiento, CURDATE()) AS edadInfractor, " +
          "p1.municipio AS municipioInfractor, p1.provincia AS provinciaInfractor, " +
          "COUNT(*) AS cantidadInfracciones " +
          "FROM infraccion i " +
          "LEFT JOIN persona p1 ON i.persona_nit = p1.nit " +
          "GROUP BY p1.sexo, edadInfractor, p1.municipio, p1.provincia " +
          "ORDER BY cantidadInfracciones DESC", nativeQuery = true)
  List<DemografiaInfractor> getDemografiaInfractores();

  // 6. Infracciones por rango de edad y sexo
  @Query(value = "SELECT CASE " +
          "WHEN TIMESTAMPDIFF(YEAR, p1.fecha_nacimiento, CURDATE()) BETWEEN 0 AND 17 THEN '0-17' " +
          "WHEN TIMESTAMPDIFF(YEAR, p1.fecha_nacimiento, CURDATE()) BETWEEN 18 AND 25 THEN '18-25' " +
          "WHEN TIMESTAMPDIFF(YEAR, p1.fecha_nacimiento, CURDATE()) BETWEEN 26 AND 40 THEN '26-40' " +
          "WHEN TIMESTAMPDIFF(YEAR, p1.fecha_nacimiento, CURDATE()) BETWEEN 41 AND 60 THEN '41-60' " +
          "ELSE '60+' END AS tramoEdad, " +
          "p1.sexo AS sexoInfractor, " +
          "COUNT(*) AS cantidadInfracciones " +
          "FROM infraccion i " +
          "LEFT JOIN persona p1 ON i.persona_nit = p1.nit " +
          "GROUP BY tramoEdad, p1.sexo " +
          "ORDER BY cantidadInfracciones DESC", nativeQuery = true)
  List<InfraccionPorEdadSexo> getInfraccionesPorEdadYSexo();

  // 7. Infracciones por marca y modelo de vehículo
  @Query(value = "SELECT m2.nombre AS marca, m.nombre AS modelo, COUNT(*) AS cantidadInfracciones " +
          "FROM infraccion i " +
          "LEFT JOIN vehiculo v ON i.vehiculo_bastidor = v.bastidor " +
          "LEFT JOIN modelo m ON v.modelo_id = m.id " +
          "LEFT JOIN marca m2 ON m.marca_id = m2.id " +
          "GROUP BY m2.nombre, m.nombre " +
          "ORDER BY cantidadInfracciones DESC", nativeQuery = true)
  List<InfraccionPorVehiculo> getInfraccionesPorMarcaYModelo();
}

