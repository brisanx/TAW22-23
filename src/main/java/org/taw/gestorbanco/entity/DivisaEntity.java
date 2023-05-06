package org.taw.gestorbanco.entity;

import org.taw.gestorbanco.dto.DivisaDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

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
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(simbolo, that.simbolo) && Objects.equals(ratioDeCambio, that.ratioDeCambio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, simbolo, ratioDeCambio);
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
    public DivisaDTO toDTO() {
        DivisaDTO divisaDTO = new DivisaDTO();
        divisaDTO.setId(this.id);
        divisaDTO.setNombre(this.nombre);
        divisaDTO.setSimbolo(this.simbolo);
        divisaDTO.setRatioDeCambio(this.ratioDeCambio);

        return divisaDTO;
    }
}
