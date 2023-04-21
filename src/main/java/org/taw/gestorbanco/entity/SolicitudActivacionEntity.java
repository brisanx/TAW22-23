package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "solicitud_activacion", schema = "gestor_banco", catalog = "")
public class SolicitudActivacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fecha_solicitud", nullable = false)
    private Timestamp fechaSolicitud;
    @Basic
    @Column(name = "aprobada", nullable = true)
    private Byte aprobada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Byte getAprobada() {
        return aprobada;
    }

    public void setAprobada(Byte aprobada) {
        this.aprobada = aprobada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudActivacionEntity that = (SolicitudActivacionEntity) o;
        return id == that.id && Objects.equals(fechaSolicitud, that.fechaSolicitud) && Objects.equals(aprobada, that.aprobada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaSolicitud, aprobada);
    }
}
