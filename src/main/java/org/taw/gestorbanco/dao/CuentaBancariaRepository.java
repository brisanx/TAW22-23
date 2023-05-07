package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;

import java.util.List;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {
    @Query("select c from CuentaBancariaEntity c where c.activo=1")
    List<CuentaBancariaEntity> cuentasActivas();

}
