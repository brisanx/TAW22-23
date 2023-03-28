package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "solicitud_alta", schema = "gestor_banco", catalog = "")
public class SolicitudAltaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_solicitud", nullable = false)
    private int idSolicitud;
    @Basic
    @Column(name = "fecha_solicitud", nullable = false)
    private Date fechaSolicitud;
    @Basic
    @Column(name = "estado", nullable = true, length = 15)
    private String estado;

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudAltaEntity that = (SolicitudAltaEntity) o;
        return idSolicitud == that.idSolicitud && Objects.equals(fechaSolicitud, that.fechaSolicitud) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolicitud, fechaSolicitud, estado);
    }
}
