package org.taw.gestorbanco.entity;

import org.taw.gestorbanco.dto.SolicitudActivacionDTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "solicitud_activacion", schema = "gestor_banco", catalog = "")
public class SolicitudActivacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "fecha_solicitud", nullable = false)
    private Timestamp fechaSolicitud;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @ManyToOne
    @JoinColumn(name = "empleado_id_gestor", referencedColumnName = "id_gestor", nullable = false)
    private EmpleadoEntity empleadoByEmpleadoIdGestor;
    @ManyToOne
    @JoinColumn(name = "cuenta_bancaria_id", referencedColumnName = "id", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaBancariaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudActivacionEntity that = (SolicitudActivacionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fechaSolicitud, that.fechaSolicitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaSolicitud);
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public EmpleadoEntity getEmpleadoByEmpleadoIdGestor() {
        return empleadoByEmpleadoIdGestor;
    }

    public void setEmpleadoByEmpleadoIdGestor(EmpleadoEntity empleadoByEmpleadoIdGestor) {
        this.empleadoByEmpleadoIdGestor = empleadoByEmpleadoIdGestor;
    }

    public CuentaBancariaEntity getCuentaBancariaByCuentaBancariaId() {
        return cuentaBancariaByCuentaBancariaId;
    }

    public void setCuentaBancariaByCuentaBancariaId(CuentaBancariaEntity cuentaBancariaByCuentaBancariaId) {
        this.cuentaBancariaByCuentaBancariaId = cuentaBancariaByCuentaBancariaId;
    }

    public SolicitudActivacionDTO toDTO() {
        SolicitudActivacionDTO solicitudActivacionDTO = new SolicitudActivacionDTO();
        solicitudActivacionDTO.setId(id);
        solicitudActivacionDTO.setFechaSolicitud(fechaSolicitud);
        solicitudActivacionDTO.setUsuarioByUsuarioId(usuarioByUsuarioId.toDTO());
        solicitudActivacionDTO.setEmpleadoByEmpleadoIdGestor(empleadoByEmpleadoIdGestor.toDTO());
        solicitudActivacionDTO.setCuentaBancariaByCuentaBancariaId(cuentaBancariaByCuentaBancariaId.toDTO());
        return solicitudActivacionDTO;
    }
}