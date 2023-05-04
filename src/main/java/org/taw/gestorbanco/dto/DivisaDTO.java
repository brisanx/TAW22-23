package org.taw.gestorbanco.dto;

import java.math.BigDecimal;

public class DivisaDTO {
    private Integer id;
    private String nombre;
    private String simbolo;
    private Double ratioDeCambio;

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
}
