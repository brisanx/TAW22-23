package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.CuentaBancariaEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Jose Torres
 */
public class OperacionBancariaDTO implements Serializable {
    private Integer id;
    private Timestamp fecha;
    private Double cantidad;
    private CuentaBancariaEntity cuentaBancariaByIdCuentaOrigen;
    private CuentaBancariaEntity cuentaBancariaByIdCuentaDestino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public CuentaBancariaEntity getCuentaBancariaByIdCuentaOrigen() {
        return cuentaBancariaByIdCuentaOrigen;
    }

    public void setCuentaBancariaByIdCuentaOrigen(CuentaBancariaEntity cuentaBancariaByIdCuentaOrigen) {
        this.cuentaBancariaByIdCuentaOrigen = cuentaBancariaByIdCuentaOrigen;
    }

    public CuentaBancariaEntity getCuentaBancariaByIdCuentaDestino() {
        return cuentaBancariaByIdCuentaDestino;
    }

    public void setCuentaBancariaByIdCuentaDestino(CuentaBancariaEntity cuentaBancariaByIdCuentaDestino) {
        this.cuentaBancariaByIdCuentaDestino = cuentaBancariaByIdCuentaDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperacionBancariaDTO that = (OperacionBancariaDTO) o;
        return getId().equals(that.getId()) && getCuentaBancariaByIdCuentaOrigen().equals(that.getCuentaBancariaByIdCuentaOrigen()) && getCuentaBancariaByIdCuentaDestino().equals(that.getCuentaBancariaByIdCuentaDestino());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCuentaBancariaByIdCuentaOrigen(), getCuentaBancariaByIdCuentaDestino());
    }
}
