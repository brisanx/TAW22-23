package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "mensaje", schema = "gestor_banco", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_mensaje", nullable = false)
    private Integer idMensaje;
    @Basic
    @Column(name = "longitud", nullable = true)
    private Integer longitud;
    @Basic
    @Column(name = "texto", nullable = true, length = 500)
    private String texto;
    @Basic
    @Column(name = "sender", nullable = true, length = 500)
    private String sender;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "conversacion_id_conver", referencedColumnName = "id_conver", nullable = false)
    private ConversacionEntity conversacionByConversacionIdConver;

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return Objects.equals(idMensaje, that.idMensaje) && Objects.equals(longitud, that.longitud) && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, longitud, texto);
    }

    public ConversacionEntity getConversacionByConversacionIdConver() {
        return conversacionByConversacionIdConver;
    }

    public void setConversacionByConversacionIdConver(ConversacionEntity conversacionByConversacionIdConver) {
        this.conversacionByConversacionIdConver = conversacionByConversacionIdConver;
    }
}