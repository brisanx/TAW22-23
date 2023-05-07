package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.sql.Timestamp;
import java.util.List;

public interface OperacionBancariaRepository extends JpaRepository<OperacionBancariaEntity, Integer> {

    @Query("SELECT op FROM OperacionBancariaEntity op WHERE op.cuentaBancariaByIdCuentaOrigen.id = :cuentaId")
    List<OperacionBancariaEntity> buscarOperacionesClientes(@Param("cuentaId")Integer cuentaBancariaId);

    @Query("SELECT op FROM OperacionBancariaEntity op WHERE op.usuarioByUsuario.id = :usuarioId")
    List<OperacionBancariaEntity> buscarOperacionesClientesUsr(@Param("usuarioId")Integer usuarioId);

    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.fecha <= :limite order by op.fecha desc")
    public List<OperacionBancariaEntity> opsMenorFecha(List<OperacionBancariaEntity> opUser, Timestamp limite);

    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.fecha >= :limite order by op.fecha desc")
    public List<OperacionBancariaEntity> opsMayorFecha(List<OperacionBancariaEntity> opUser, Timestamp limite);

    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.cantidad >= :cantidad")
    public List<OperacionBancariaEntity> opsMayorCantidad(List<OperacionBancariaEntity> opUser, Double cantidad);

    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.cantidad <= :cantidad")
    public List<OperacionBancariaEntity> opsMenorCantidad(List<OperacionBancariaEntity> opUser, Double cantidad);
    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.cantidad <= :cantidad " +
            "and op.fecha <= :limite order by op.fecha desc")
    public List<OperacionBancariaEntity> opsMeMe(List<OperacionBancariaEntity> opUser, Double cantidad, Timestamp limite);
    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.cantidad <= :cantidad " +
            "and op.fecha >= :limite order by op.fecha desc")
    public List<OperacionBancariaEntity> opsMaMe(List<OperacionBancariaEntity> opUser, Double cantidad, Timestamp limite);
    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.cantidad >= :cantidad " +
            "and op.fecha <= :limite order by op.fecha desc")
    public List<OperacionBancariaEntity> opsMeMa(List<OperacionBancariaEntity> opUser, Double cantidad, Timestamp limite);
    @Query("select op from OperacionBancariaEntity op where op in :opUser and op.cantidad >= :cantidad " +
            "and op.fecha >= :limite order by op.fecha desc")
    public List<OperacionBancariaEntity> opsMaMa(List<OperacionBancariaEntity> opUser, Double cantidad, Timestamp limite);
}
