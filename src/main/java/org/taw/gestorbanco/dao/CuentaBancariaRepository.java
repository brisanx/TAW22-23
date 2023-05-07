package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo, Óscar Hidalgo Puertas
 */
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {

}

