package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "divisa", schema = "gestor_banco", catalog = "")
public class DivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Basic
    @Column(name = "simbolo", nullable = false, length = 10)
    private String simbolo;
    @Basic
    @Column(name = "ratio_de_cambio", nullable = false, precision = 2)
    private Double ratioDeCambio;
    @OneToMany(mappedBy = "divisaByDivisaId")
    private Collection<CuentaBancariaEntity> cuentaBancariasById;
    @OneToMany(mappedBy = "divisaByDivisaId")
    private Collection<SolicitudAltaEntity> solicitudAltasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Double getRatioDeCambio() {
        return ratioDeCambio;
    }

    public void setRatioDeCambio(Double ratioDeCambio) {
        this.ratioDeCambio = ratioDeCambio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivisaEntity that = (DivisaEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (simbolo != null ? !simbolo.equals(that.simbolo) : that.simbolo != null) return false;
        if (ratioDeCambio != null ? !ratioDeCambio.equals(that.ratioDeCambio) : that.ratioDeCambio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (simbolo != null ? simbolo.hashCode() : 0);
        result = 31 * result + (ratioDeCambio != null ? ratioDeCambio.hashCode() : 0);
        return result;
    }

    public Collection<CuentaBancariaEntity> getCuentaBancariasById() {
        return cuentaBancariasById;
    }

    public void setCuentaBancariasById(Collection<CuentaBancariaEntity> cuentaBancariasById) {
        this.cuentaBancariasById = cuentaBancariasById;
    }

    public Collection<SolicitudAltaEntity> getSolicitudAltasById() {
        return solicitudAltasById;
    }

    public void setSolicitudAltasById(Collection<SolicitudAltaEntity> solicitudAltasById) {
        this.solicitudAltasById = solicitudAltasById;
    }
}
