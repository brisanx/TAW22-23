package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "divisa", schema = "gestor_banco", catalog = "")
public class DivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Basic
    @Column(name = "simbolo", nullable = false, length = 10)
    private String simbolo;
    @Basic
    @Column(name = "ratio_de_cambio", nullable = false, precision = 2)
    private BigDecimal ratioDeCambio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public BigDecimal getRatioDeCambio() {
        return ratioDeCambio;
    }

    public void setRatioDeCambio(BigDecimal ratioDeCambio) {
        this.ratioDeCambio = ratioDeCambio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisaEntity that = (DivisaEntity) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(simbolo, that.simbolo) && Objects.equals(ratioDeCambio, that.ratioDeCambio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, simbolo, ratioDeCambio);
    }
}
