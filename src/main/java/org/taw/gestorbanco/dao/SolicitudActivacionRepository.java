package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.entity.SolicitudAltaEntity;

/**
 * @author Jose Torres
 */
public interface SolicitudActivacionRepository extends JpaRepository<SolicitudActivacionEntity, Integer> {
}
