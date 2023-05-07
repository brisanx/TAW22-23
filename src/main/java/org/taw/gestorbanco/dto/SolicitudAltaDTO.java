package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.io.Serializable;
import java.sql.Date;

public class SolicitudAltaDTO implements Serializable {
    private Integer id;
    private Date fechaSolicitud;
    private EmpleadoEntity empleadoByIdGestor;
    private UsuarioEntity usuarioByUsuarioId;
    private DivisaEntity divisaByDivisaId;

    public SolicitudAltaDTO() {
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
