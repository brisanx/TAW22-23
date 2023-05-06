package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

public interface SolicitudActivacionRepository extends JpaRepository<SolicitudActivacionEntity, Integer> {
    @Query("select s from SolicitudActivacionEntity s where s.usuarioByUsuarioId=:usuario and s.cuentaBancariaByCuentaBancariaId=:cuenta")
    SolicitudActivacionEntity buscarSolicitudActivacionPorUsuarioYCuenta(@Param("usuario")UsuarioEntity usuario, @Param("cuenta")CuentaBancariaEntity cuenta);
}
