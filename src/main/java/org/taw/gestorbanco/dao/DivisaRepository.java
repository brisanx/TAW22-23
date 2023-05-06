package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.DivisaEntity;

public interface DivisaRepository extends JpaRepository<DivisaEntity, Integer> {
    @Query("select d from DivisaEntity d where d.nombre= :moneda")
    DivisaEntity buscarPorMoneda(@Param("moneda") String moneda);
}
