package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.SolicitudAltaEntity;

public interface AltaRepository extends JpaRepository<SolicitudAltaEntity, Integer> {
}
