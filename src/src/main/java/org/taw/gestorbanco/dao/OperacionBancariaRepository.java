package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.util.List;

public interface OperacionBancariaRepository extends JpaRepository<OperacionBancariaEntity, Integer> {
    @Query("select o from OperacionBancariaEntity o where o.cuentaBancariaByIdCuentaOrigen.id= :id")
    List<OperacionBancariaEntity> buscarOperacionesEmpresa(@Param("id") Integer id);
}
