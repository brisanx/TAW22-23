package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.AsignacionEntityPK;

import java.util.List;

public interface AsignacionRepository extends JpaRepository<AsignacionEntity, AsignacionEntityPK> {
    @Query("select asi.cuentaBancariaId from AsignacionEntity asi where asi.usuarioId = :user ")
    List<Integer> cuentasAsignadasPorUsuario(Integer user);

    @Query("select a.cuentaBancariaId from AsignacionEntity a where a.usuarioId = :id")
    List<Integer> findByUsuarioId(Integer id);
}
