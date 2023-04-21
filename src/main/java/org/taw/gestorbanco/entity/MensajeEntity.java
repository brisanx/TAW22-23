package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "gestor_banco", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_mensaje", nullable = false)
    private int idMensaje;
    @Basic
    @Column(name = "longitud", nullable = true)
    private Integer longitud;
    @Basic
    @Column(name = "texto", nullable = true, length = 45)
    private String texto;

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return idMensaje == that.idMensaje && Objects.equals(longitud, that.longitud) && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, longitud, texto);
    }
}
