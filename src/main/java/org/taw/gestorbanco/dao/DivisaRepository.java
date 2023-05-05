package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.DivisaEntity;

public interface DivisaRepository extends JpaRepository<DivisaEntity, Integer> {
}
