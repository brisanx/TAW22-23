package org.taw.gestorbanco.entity;

import org.taw.gestorbanco.dto.AsignacionDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "asignacion", schema = "gestor_banco", catalog = "")
@IdClass(AsignacionEntityPK.class)
public class AsignacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cuenta_bancaria_id", nullable = false)
    private Integer cuentaBancariaId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
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
        AsignacionEntity that = (AsignacionEntity) o;
        return Objects.equals(cuentaBancariaId, that.cuentaBancariaId) && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuentaBancariaId, usuarioId);
    }

    public AsignacionDTO toDTO() {
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        asignacionDTO.setCuentaBancariaId(cuentaBancariaId);
        asignacionDTO.setUsuarioId(usuarioId);
        return asignacionDTO;
    }
}