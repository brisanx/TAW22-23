package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private UsuarioEntity usuarioByUsuario;

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
        return Objects.equals(id, that.id) && Objects.equals(fecha, that.fecha) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, cantidad);
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

    public UsuarioEntity getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(UsuarioEntity usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }
}
