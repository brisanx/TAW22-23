package org.taw.gestorbanco.entity;

import org.taw.gestorbanco.dto.OperacionBancariaDTO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "operacion_bancaria", schema = "gestor_banco", catalog = "")
public class OperacionBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "cantidad", nullable = false, precision = 0)
    private Double cantidad;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_origen", referencedColumnName = "id", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByIdCuentaOrigen;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_destino", referencedColumnName = "id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacionBancariaEntity that = (OperacionBancariaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
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

    public OperacionBancariaDTO toDTO() {
        OperacionBancariaDTO dto = new OperacionBancariaDTO();
        dto.setId(id);
        dto.setFecha(fecha);
        dto.setCantidad(cantidad);

        return dto;
    }
}
