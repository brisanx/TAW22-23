package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

public interface OperacionBancariaRepository extends JpaRepository<OperacionBancariaEntity, Integer> {
}
