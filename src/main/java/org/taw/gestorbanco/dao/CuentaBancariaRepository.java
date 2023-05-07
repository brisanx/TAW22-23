package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {
}
