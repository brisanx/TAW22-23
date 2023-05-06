package org.taw.gestorbanco.entity;

import org.taw.gestorbanco.dto.EmpleadoDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "gestor_banco", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_gestor", nullable = false)
    private Integer idGestor;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "contrasena", nullable = false, length = 45)
    private String contrasena;
    @Basic
    @Column(name = "rol", nullable = false, length = 45)
    private String rol;
    @OneToMany(mappedBy = "empleadoByEmpleadoIdGestor")
    private Collection<ConversacionEntity> conversacionsByIdGestor;
    @OneToMany(mappedBy = "empleadoByEmpleadoIdGestor")
    private Collection<SolicitudActivacionEntity> solicitudActivacionsByIdGestor;
    @OneToMany(mappedBy = "empleadoByIdGestor")
    private Collection<SolicitudAltaEntity> solicitudAltasByIdGestor;

    public Integer getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(Integer idGestor) {
        this.idGestor = idGestor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoEntity that = (EmpleadoEntity) o;
        return Objects.equals(idGestor, that.idGestor) && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(contrasena, that.contrasena) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestor, nombre, email, contrasena, rol);
    }

    public Collection<ConversacionEntity> getConversacionsByIdGestor() {
        return conversacionsByIdGestor;
    }

    public void setConversacionsByIdGestor(Collection<ConversacionEntity> conversacionsByIdGestor) {
        this.conversacionsByIdGestor = conversacionsByIdGestor;
    }

    public Collection<SolicitudActivacionEntity> getSolicitudActivacionsByIdGestor() {
        return solicitudActivacionsByIdGestor;
    }

    public void setSolicitudActivacionsByIdGestor(Collection<SolicitudActivacionEntity> solicitudActivacionsByIdGestor) {
        this.solicitudActivacionsByIdGestor = solicitudActivacionsByIdGestor;
    }

    public Collection<SolicitudAltaEntity> getSolicitudAltasByIdGestor() {
        return solicitudAltasByIdGestor;
    }

    public void setSolicitudAltasByIdGestor(Collection<SolicitudAltaEntity> solicitudAltasByIdGestor) {
        this.solicitudAltasByIdGestor = solicitudAltasByIdGestor;
    }
    public EmpleadoDTO toDTO() {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setIdGestor(this.idGestor);
        empleadoDTO.setNombre(this.nombre);
        empleadoDTO.setEmail(this.email);
        empleadoDTO.setRol(this.rol);

        return empleadoDTO;
    }
}
