package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

public interface ActivacionRepository extends JpaRepository<SolicitudActivacionEntity, Integer> {
    @Query("select s from SolicitudActivacionEntity s where s.usuarioByUsuarioId=:usuario and s.cuentaBancariaByCuentaBancariaId=:cuenta")
    SolicitudActivacionEntity buscarSolicitudActivacionPorUsuarioYCuenta(UsuarioEntity usuario, CuentaBancariaEntity cuenta);
}
