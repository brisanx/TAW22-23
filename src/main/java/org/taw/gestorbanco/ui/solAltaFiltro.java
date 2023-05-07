package org.taw.gestorbanco.ui;
/**
 * @author Fernando Calvo Díaz
 */
public class solAltaFiltro{
    private Integer solId;
    private Integer user;
    private String tipo;
    private Integer moneda;
    private double saldo;

    public solAltaFiltro() {
    }

    public Integer getSolId() {
        return solId;
    }

    public void setSolId(Integer solId) {
        this.solId = solId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}