package org.taw.gestorbanco.dto;

public class AsignacionDTO {
    private Integer userId;
    private Integer cuentaId;

    public AsignacionDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }
}
