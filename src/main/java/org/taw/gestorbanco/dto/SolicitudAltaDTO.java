package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.sql.Timestamp;
import java.util.Objects;
/**
 * @author Alba Sánchez Ibáñez
 */
public class SolicitudAltaDTO {
    private Integer idSolicitud;
    private Timestamp fechaSolicitud;
    private EmpleadoDTO empleadoByIdGestor;
    private UsuarioDTO usuarioByUsuarioId;
    private DivisaDTO divisaByDivisaId;

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EmpleadoDTO getEmpleadoByIdGestor() {
        return empleadoByIdGestor;
    }

    public void setEmpleadoByIdGestor(EmpleadoDTO empleadoByIdGestor) {
        this.empleadoByIdGestor = empleadoByIdGestor;
    }

    public UsuarioDTO getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioDTO usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public DivisaDTO getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaDTO divisaByDivisaId){
        this.divisaByDivisaId = divisaByDivisaId;
    }
}
