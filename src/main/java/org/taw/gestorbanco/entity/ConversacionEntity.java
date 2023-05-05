package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "conversacion", schema = "gestor_banco", catalog = "")
public class ConversacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_conver", nullable = false)
    private Integer idConver;
    @Basic
    @Column(name = "numero_mensaje", nullable = true)
    private Integer numeroMensaje;
    @ManyToOne
    @JoinColumn(name = "empleado_id_gestor", referencedColumnName = "id_gestor", nullable = false)
    private EmpleadoEntity empleadoByEmpleadoIdGestor;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @OneToMany(mappedBy = "conversacionByConversacionIdConver")
    private Collection<MensajeEntity> mensajesByIdConver;

    public Integer getIdConver() {
        return idConver;
    }

    public void setIdConver(Integer idConver) {
        this.idConver = idConver;
    }

    public Integer getNumeroMensaje() {
        return numeroMensaje;
    }

    public void setNumeroMensaje(Integer numeroMensaje) {
        this.numeroMensaje = numeroMensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversacionEntity that = (ConversacionEntity) o;

        if (idConver != null ? !idConver.equals(that.idConver) : that.idConver != null) return false;
        if (numeroMensaje != null ? !numeroMensaje.equals(that.numeroMensaje) : that.numeroMensaje != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idConver != null ? idConver.hashCode() : 0;
        result = 31 * result + (numeroMensaje != null ? numeroMensaje.hashCode() : 0);
        return result;
    }

    public EmpleadoEntity getEmpleadoByEmpleadoIdGestor() {
        return empleadoByEmpleadoIdGestor;
    }

    public void setEmpleadoByEmpleadoIdGestor(EmpleadoEntity empleadoByEmpleadoIdGestor) {
        this.empleadoByEmpleadoIdGestor = empleadoByEmpleadoIdGestor;
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public Collection<MensajeEntity> getMensajesByIdConver() {
        return mensajesByIdConver;
    }

    public void setMensajesByIdConver(Collection<MensajeEntity> mensajesByIdConver) {
        this.mensajesByIdConver = mensajesByIdConver;
    }
}
