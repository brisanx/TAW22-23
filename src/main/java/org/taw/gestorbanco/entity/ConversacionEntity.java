package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

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

    @Basic
    @Column(name = "fecha_apertura", nullable = true)
    private Timestamp fechaApertura;
    @Basic
    @Column(name = "fecha_cierre", nullable = true)
    private Timestamp fechaCierre;

    @Basic
    @Column(name = "estado", nullable = true)
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "empleado_id_gestor", referencedColumnName = "id_gestor", nullable = false)
    private EmpleadoEntity empleadoByEmpleadoIdGestor;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @OneToMany(mappedBy = "conversacionByConversacionIdConver",cascade = {CascadeType.ALL})
    private Collection<MensajeEntity> mensajesByIdConver;





    public Integer getIdConver() {
        return idConver;
    }

    public void setIdConver(Integer idConver) {
        this.idConver = idConver;
    }

    public Timestamp getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Timestamp fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
        return Objects.equals(idConver, that.idConver) && Objects.equals(numeroMensaje, that.numeroMensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConver, numeroMensaje);
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
