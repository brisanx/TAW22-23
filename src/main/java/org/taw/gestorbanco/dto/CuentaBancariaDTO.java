package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;

import java.util.Collection;
import java.util.Objects;

public class CuentaBancariaDTO {
    private Integer id;
    private String moneda;
    private Double saldo;
    private Byte sospechosa;
    private Byte activo;
    private Collection<AsignacionEntity> asignacionsById;
    private DivisaEntity divisaByDivisaId;
    private Collection<OperacionBancariaEntity> operacionBancariasById;
    private Collection<OperacionBancariaEntity> operacionBancariasById_0;
    private Collection<SolicitudActivacionEntity> solicitudActivacionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Byte getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(Byte sospechosa) {
        this.sospechosa = sospechosa;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    public Collection<AsignacionEntity> getAsignacionsById() {
        return asignacionsById;
    }

    public void setAsignacionsById(Collection<AsignacionEntity> asignacionsById) {
        this.asignacionsById = asignacionsById;
    }

    public DivisaEntity getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaEntity divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
    }

    public Collection<OperacionBancariaEntity> getOperacionBancariasById() {
        return operacionBancariasById;
    }

    public void setOperacionBancariasById(Collection<OperacionBancariaEntity> operacionBancariasById) {
        this.operacionBancariasById = operacionBancariasById;
    }

    public Collection<OperacionBancariaEntity> getOperacionBancariasById_0() {
        return operacionBancariasById_0;
    }

    public void setOperacionBancariasById_0(Collection<OperacionBancariaEntity> operacionBancariasById_0) {
        this.operacionBancariasById_0 = operacionBancariasById_0;
    }

    public Collection<SolicitudActivacionEntity> getSolicitudActivacionsById() {
        return solicitudActivacionsById;
    }

    public void setSolicitudActivacionsById(Collection<SolicitudActivacionEntity> solicitudActivacionsById) {
        this.solicitudActivacionsById = solicitudActivacionsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancariaDTO that = (CuentaBancariaDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
