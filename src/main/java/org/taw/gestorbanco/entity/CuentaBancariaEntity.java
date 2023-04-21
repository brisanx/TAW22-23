package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cuenta_bancaria", schema = "gestor_banco", catalog = "")
public class CuentaBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;
    @Basic
    @Column(name = "moneda", nullable = false, length = 20)
    private String moneda;
    @Basic
    @Column(name = "saldo", nullable = false, precision = 0)
    private double saldo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancariaEntity that = (CuentaBancariaEntity) o;
        return id == that.id && Double.compare(that.saldo, saldo) == 0 && Objects.equals(tipo, that.tipo) && Objects.equals(moneda, that.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, moneda, saldo);
    }
}
