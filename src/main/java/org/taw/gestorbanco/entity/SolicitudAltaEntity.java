package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "solicitud_alta", schema = "gestor_banco", catalog = "")
public class SolicitudAltaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_solicitud", nullable = false)
    private Integer idSolicitud;
    @Basic
    @Column(name = "fecha_solicitud", nullable = false)
    private Date fechaSolicitud;
    @ManyToOne
    @JoinColumn(name = "id_gestor", referencedColumnName = "id_gestor", nullable = false)
    private EmpleadoEntity empleadoByIdGestor;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @ManyToOne
    @JoinColumn(name = "divisa_id", referencedColumnName = "id", nullable = false)
    private DivisaEntity divisaByDivisaId;

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolicitudAltaEntity that = (SolicitudAltaEntity) o;

        if (idSolicitud != null ? !idSolicitud.equals(that.idSolicitud) : that.idSolicitud != null) return false;
        if (fechaSolicitud != null ? !fechaSolicitud.equals(that.fechaSolicitud) : that.fechaSolicitud != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSolicitud != null ? idSolicitud.hashCode() : 0;
        result = 31 * result + (fechaSolicitud != null ? fechaSolicitud.hashCode() : 0);
        return result;
    }

    public EmpleadoEntity getEmpleadoByIdGestor() {
        return empleadoByIdGestor;
    }

    public void setEmpleadoByIdGestor(EmpleadoEntity empleadoByIdGestor) {
        this.empleadoByIdGestor = empleadoByIdGestor;
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public DivisaEntity getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaEntity divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
    }
}
