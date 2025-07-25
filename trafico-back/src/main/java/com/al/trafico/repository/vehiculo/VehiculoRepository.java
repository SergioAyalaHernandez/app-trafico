package com.al.trafico.repository.vehiculo;

import com.al.trafico.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
  Optional<Vehiculo> findByMatricula(String matricula);
}