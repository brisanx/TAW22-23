package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "conversacion", schema = "gestor_banco", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_conver", nullable = false)
    private int idConver;
    @Basic
    @Column(name = "nunero_mensajes", nullable = true)
    private Integer nuneroMensajes;

    public int getIdConver() {
        return idConver;
    }

    public void setIdConver(int idConver) {
        this.idConver = idConver;
    }

    public Integer getNuneroMensajes() {
        return nuneroMensajes;
    }

    public void setNuneroMensajes(Integer nuneroMensajes) {
        this.nuneroMensajes = nuneroMensajes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversacionEntity that = (ConversacionEntity) o;
        return idConver == that.idConver && Objects.equals(nuneroMensajes, that.nuneroMensajes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConver, nuneroMensajes);
    }
}
