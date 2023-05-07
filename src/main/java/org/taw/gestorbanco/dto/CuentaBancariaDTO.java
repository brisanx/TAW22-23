package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.DivisaEntity;

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

    private DivisaDTO divisaByDivisaId;


    public CuentaBancariaDTO() {
    }

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
    public DivisaDTO getDivisaByDivisaId() {
        return divisaByDivisaId;
    }

    public void setDivisaByDivisaId(DivisaDTO divisaByDivisaId) {
        this.divisaByDivisaId = divisaByDivisaId;
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