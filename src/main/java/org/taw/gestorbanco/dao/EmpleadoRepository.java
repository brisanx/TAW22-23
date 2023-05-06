package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.EmpleadoEntity;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Integer> {
        @Query("select e from EmpleadoEntity e where e.rol='gestor' ")
        List<EmpleadoEntity> todosLosGestores();
}
