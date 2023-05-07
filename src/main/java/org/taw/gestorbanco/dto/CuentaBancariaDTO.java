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

    public DivisaEntity getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaEntity divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
    }

}
