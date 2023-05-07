package org.taw.gestorbanco.filtros;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.sql.Timestamp.valueOf;

public class opbFiltro {
    private Integer id;
    private String fecha;
    private Boolean mm;
    private Boolean mmc;
    private Double cantidad;

    public opbFiltro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getMm() {
        return mm;
    }

    public void setMm(Boolean mm) {
        this.mm = mm;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getMmc() {
        return mmc;
    }

    public void setMmc(Boolean mmc) {
        this.mmc = mmc;
    }

    public Timestamp conversion(){
        LocalDate fecha = LocalDate.parse(this.fecha);
        LocalTime hora = LocalTime.of(0, 0, 0);
        LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
        Timestamp timestamp = Timestamp.valueOf(fechaHora);
        System.out.println(timestamp);
        return timestamp;
    }
}