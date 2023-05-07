package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.sql.Timestamp;
import java.util.List;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo, Óscar Hidalgo Puertas
 */
public interface OperacionBancariaRepository extends JpaRepository<OperacionBancariaEntity, Integer> {
    @Query("select o from OperacionBancariaEntity o where o.cuentaBancariaByIdCuentaOrigen.id= :id")
    List<OperacionBancariaEntity> buscarOperacionesEmpresa(@Param("id") Integer id);

    // FILTROS DE FERNANDO - ALBA
    @Query("select o from OperacionBancariaEntity o where o.id = " +
            "(select MAX(o2.id) from OperacionBancariaEntity o2)")
    public OperacionBancariaEntity ultimaOperacion();

    @Query("select distinct ob from OperacionBancariaEntity ob where ob.id in (" +
            "select MAX(ob2.id) from OperacionBancariaEntity ob2 " +
            "where ob2.cuentaBancariaByIdCuentaDestino in :cuentasSospechosas " +
            "and ob2.cuentaBancariaByIdCuentaOrigen in :cuentasCuidado " +
            "group by ob2.cuentaBancariaByIdCuentaOrigen) " +
            "order by ob.cuentaBancariaByIdCuentaOrigen.id")
    List<OperacionBancariaEntity> ultimasOperacionesSospechosas(List<CuentaBancariaEntity> cuentasSospechosas,
                                                                List<CuentaBancariaEntity> cuentasCuidado);

    @Query("select op from OperacionBancariaEntity op where " +
            "op.cuentaBancariaByIdCuentaOrigen.id in :as " +
            " order by op.cuentaBancariaByIdCuentaOrigen.id, op.fecha desc")
    public List<OperacionBancariaEntity> operacionesPorAsignacion(List<Integer> as);

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
