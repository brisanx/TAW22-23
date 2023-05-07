package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;

import java.sql.Timestamp;

public class SolicitudActivacionDTO {
    private Integer id;
    private Timestamp fechaSolicitud;
    private UsuarioDTO usuarioByUsuarioId;
    private EmpleadoDTO empleadoByEmpleadoIdGestor;
    private CuentaBancariaDTO cuentaBancariaByCuentaBancariaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public UsuarioDTO getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioDTO usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public EmpleadoDTO getEmpleadoByEmpleadoIdGestor() {
        return empleadoByEmpleadoIdGestor;
    }

    public void setEmpleadoByEmpleadoIdGestor(EmpleadoDTO empleadoByEmpleadoIdGestor) {
        this.empleadoByEmpleadoIdGestor = empleadoByEmpleadoIdGestor;
    }

    public CuentaBancariaDTO getCuentaBancariaByCuentaBancariaId() {
        return cuentaBancariaByCuentaBancariaId;
    }

    public void setCuentaBancariaByCuentaBancariaId(CuentaBancariaDTO cuentaBancariaByCuentaBancariaId) {
        this.cuentaBancariaByCuentaBancariaId = cuentaBancariaByCuentaBancariaId;
    }
}