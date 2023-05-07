package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Jose Torres
 */
public class CuentaBancariaDTO {
    private Integer id;
    private String moneda;
    private Double saldo;
    private Byte sospechosa;
    private Byte activo;
    private Collection<AsignacionDTO> asignacionsById;
    private DivisaDTO divisaByDivisaId;
    private Collection<OperacionBancariaDTO> operacionBancariasById;
    private Collection<OperacionBancariaDTO> operacionBancariasById_0;
    private Collection<SolicitudActivacionDTO> solicitudActivacionsById;

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

    public Collection<AsignacionDTO> getAsignacionsById() {
        return asignacionsById;
    }

    public void setAsignacionsById(Collection<AsignacionDTO> asignacionsById) {
        this.asignacionsById = asignacionsById;
    }

    public DivisaDTO getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaDTO divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
    }

    public Collection<OperacionBancariaDTO> getOperacionBancariasById() {
        return operacionBancariasById;
    }

    public void setOperacionBancariasById(Collection<OperacionBancariaDTO> operacionBancariasById) {
        this.operacionBancariasById = operacionBancariasById;
    }

    public Collection<OperacionBancariaDTO> getOperacionBancariasById_0() {
        return operacionBancariasById_0;
    }

    public void setOperacionBancariasById_0(Collection<OperacionBancariaDTO> operacionBancariasById_0) {
        this.operacionBancariasById_0 = operacionBancariasById_0;
    }

    public Collection<SolicitudActivacionDTO> getSolicitudActivacionsById() {
        return solicitudActivacionsById;
    }

    public void setSolicitudActivacionsById(Collection<SolicitudActivacionDTO> solicitudActivacionsById) {
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