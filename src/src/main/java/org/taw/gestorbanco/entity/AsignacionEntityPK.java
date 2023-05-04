package org.taw.gestorbanco.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AsignacionEntityPK implements Serializable {
    @Column(name = "cuenta_bancaria_id", nullable = false)
    @Id
    private Integer cuentaBancariaId;

    @Column(name = "usuario_id", nullable = false)
    @Id
    private Integer usuarioId;

    public AsignacionEntityPK() {}

    public AsignacionEntityPK(Integer cuentaBancariaId, Integer usuarioId) {
        this.cuentaBancariaId = cuentaBancariaId;
        this.usuarioId = usuarioId;
    }

    public Integer getCuentaBancariaId() {
        return cuentaBancariaId;
    }

    public void setCuentaBancariaId(Integer cuentaBancariaId) {
        this.cuentaBancariaId = cuentaBancariaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignacionEntityPK that = (AsignacionEntityPK) o;
        return Objects.equals(cuentaBancariaId, that.cuentaBancariaId) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuentaBancariaId, usuarioId);
    }
}