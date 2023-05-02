package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {
}
