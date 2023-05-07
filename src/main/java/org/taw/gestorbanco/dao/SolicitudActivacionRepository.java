package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.entity.SolicitudAltaEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.util.List;

public interface SolicitudActivacionRepository extends JpaRepository<SolicitudActivacionEntity, Integer> {
    @Query("select sol from SolicitudActivacionEntity sol where sol.usuarioByUsuarioId=:usuario and sol.cuentaBancariaByCuentaBancariaId=:cuenta")
    SolicitudActivacionEntity buscarSolicitudActivacionPorUsuarioYCuenta(UsuarioEntity usuario, CuentaBancariaEntity cuenta);
}
