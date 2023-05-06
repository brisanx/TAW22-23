package org.taw.gestorbanco.dto;

import java.io.Serializable;

/**
 * @author Jose Torres
 */
public class AsignacionDTO implements Serializable {
    private Integer cuentaBancariaId;
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
}
