package org.taw.gestorbanco.entity;

import javax.persistence.*;

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

        if (idMensaje != null ? !idMensaje.equals(that.idMensaje) : that.idMensaje != null) return false;
        if (longitud != null ? !longitud.equals(that.longitud) : that.longitud != null) return false;
        if (texto != null ? !texto.equals(that.texto) : that.texto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMensaje != null ? idMensaje.hashCode() : 0;
        result = 31 * result + (longitud != null ? longitud.hashCode() : 0);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        return result;
    }

    public ConversacionEntity getConversacionByConversacionIdConver() {
        return conversacionByConversacionIdConver;
    }

    public void setConversacionByConversacionIdConver(ConversacionEntity conversacionByConversacionIdConver) {
        this.conversacionByConversacionIdConver = conversacionByConversacionIdConver;
    }
}
