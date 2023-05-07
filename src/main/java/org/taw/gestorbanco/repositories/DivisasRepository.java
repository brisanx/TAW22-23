package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.DivisaEntity;

public interface DivisasRepository extends JpaRepository<DivisaEntity, Integer> {
    @Query("select d from DivisaEntity d where d.nombre= :moneda")
    DivisaEntity buscarPorMoneda(String moneda);
}
