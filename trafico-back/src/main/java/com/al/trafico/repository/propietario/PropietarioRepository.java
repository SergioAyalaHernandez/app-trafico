package com.al.trafico.repository.propietario;

import com.al.trafico.entity.PropiedadVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<PropiedadVehiculo, Long> {

}
