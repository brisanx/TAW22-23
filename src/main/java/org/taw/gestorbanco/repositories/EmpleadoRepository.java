package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Integer> {
}
