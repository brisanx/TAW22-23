package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.AsignacionEntityPK;

public interface AsignacionRepository extends JpaRepository<AsignacionEntity, AsignacionEntityPK> {
}
