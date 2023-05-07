package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.SolicitudAltaEntity;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo, Óscar Hidalgo Puertas
 */
public interface SolicitudAltaRepository extends JpaRepository<SolicitudAltaEntity,Integer> {
}