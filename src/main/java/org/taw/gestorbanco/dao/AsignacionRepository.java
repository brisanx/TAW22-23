package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.AsignacionEntityPK;

public interface AsignacionRepository extends JpaRepository<AsignacionEntity, AsignacionEntityPK> {

    @Query("select a from AsignacionEntity a where a.usuarioId= :id")
    AsignacionEntity findByUsuarioId(@Param("id") Integer id);
}
