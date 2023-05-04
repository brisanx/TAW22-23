package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;

import java.sql.Timestamp;
import java.util.List;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {
    @Query("select distinct c from CuentaBancariaEntity c where c.activo = :uno and not exists (" +
            "select o from OperacionBancariaEntity o where (o.cuentaBancariaByIdCuentaOrigen.id = c.id" +
            " or o.cuentaBancariaByIdCuentaDestino.id = c.id) and o.fecha >= :limite) order by c.id")
    public List<CuentaBancariaEntity> filtroFecha(Timestamp limite, Byte uno);

    @Query("SELECT MAX(o.fecha) FROM OperacionBancariaEntity o " +
            "WHERE o.cuentaBancariaByIdCuentaOrigen IN :lista OR o.cuentaBancariaByIdCuentaDestino IN :lista " +
            "GROUP BY CASE " +
            "           WHEN o.cuentaBancariaByIdCuentaOrigen IN :lista THEN o.cuentaBancariaByIdCuentaOrigen.id " +
            "           ELSE o.cuentaBancariaByIdCuentaDestino.id " +
            "         END")
    public List<Timestamp> verFechas(List<CuentaBancariaEntity> lista);


    @Query("select c from CuentaBancariaEntity c where c.sospechosa = :uno")
    public List<CuentaBancariaEntity> cuentasSospechosas(Byte uno);


    @Query("select c from CuentaBancariaEntity c where c not in :lista")
    public List<CuentaBancariaEntity> noSospechosos(List<CuentaBancariaEntity> lista);

    @Query("select c from CuentaBancariaEntity c where c.id = " +
            "(select MAX(c2.id) from CuentaBancariaEntity c2)")
    public CuentaBancariaEntity ultimaCuenta();

    @Query("select distinct c from CuentaBancariaEntity c where exists (" +
            "select o from OperacionBancariaEntity o where " +
            "o.cuentaBancariaByIdCuentaOrigen = c and " +
            "o.cuentaBancariaByIdCuentaDestino in (:sospechosas) and " +
            "o.cuentaBancariaByIdCuentaOrigen != o.cuentaBancariaByIdCuentaDestino and " +
            "o.cuentaBancariaByIdCuentaOrigen.activo = :uno) " +
            "order by c.id")
    public List<CuentaBancariaEntity> encontrarTransferenciasSospechosas(List<CuentaBancariaEntity> sospechosas, Byte uno);
}
