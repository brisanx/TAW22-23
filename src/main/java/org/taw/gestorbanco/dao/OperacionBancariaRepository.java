package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.util.List;

public interface OperacionBancariaRepository extends JpaRepository<OperacionBancariaEntity, Integer> {

    @Query("SELECT op FROM OperacionBancariaEntity op WHERE op.cuentaBancariaByIdCuentaOrigen.id = :cuentaId")
    List<OperacionBancariaEntity> buscarOperacionesClientes(@Param("cuentaId")Integer cuentaBancariaId);

    @Query("SELECT op FROM OperacionBancariaEntity op WHERE op.usuarioByUsuario.id = :usuarioId")
    List<OperacionBancariaEntity> buscarOperacionesClientesUsr(@Param("usuarioId")Integer usuarioId);
}
