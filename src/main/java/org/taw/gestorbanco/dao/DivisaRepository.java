package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.DivisaEntity;

public interface DivisaRepository extends JpaRepository<DivisaEntity, Integer> {

    @Query("select div from DivisaEntity div where div.nombre = :nombre")
    DivisaEntity buscarDivisaPorNombre(String nombre);
}
