package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;

public interface ActivacionRepository extends JpaRepository<SolicitudActivacionEntity, Integer> {
}
