package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.DivisaEntity;

public interface DivisasRepository extends JpaRepository<DivisaEntity, Integer> {
}
