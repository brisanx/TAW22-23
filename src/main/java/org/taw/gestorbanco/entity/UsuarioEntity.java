package org.taw.gestorbanco.entity;

import org.taw.gestorbanco.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "gestor_banco", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "identificacion", nullable = false, length = 11)
    private String identificacion;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "apellido", nullable = true, length = 50)
    private String apellido;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "contrasena", nullable = false, length = 50)
    private String contrasena;
    @Basic
    @Column(name = "rol", nullable = false, length = 45)
    private String rol;
    @Basic
    @Column(name = "subrol", nullable = true, length = 45)
    private String subrol;
    @Basic
    @Column(name = "direccion", nullable = true, length = 45)
    private String direccion;
    @Basic
    @Column(name = "telefono", nullable = true, length = 12)
    private String telefono;
    @Basic
    @Column(name = "bloqueo", nullable = true)
    private Boolean bloqueo;
    @OneToMany(mappedBy = "usuarioId")
    private Collection<AsignacionEntity> asignacionsById;
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<ConversacionEntity> conversacionsById;
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<SolicitudActivacionEntity> solicitudActivacionsById;
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<SolicitudAltaEntity> solicitudAltasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getSubrol() {
        return subrol;
    }

    public void setSubrol(String subrol) {
        this.subrol = subrol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Boolean bloqueo){
        this.bloqueo = bloqueo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(identificacion, that.identificacion) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email) && Objects.equals(contrasena, that.contrasena) && Objects.equals(rol, that.rol) && Objects.equals(subrol, that.subrol) && Objects.equals(direccion, that.direccion) && Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificacion, nombre, apellido, email, contrasena, rol, subrol, direccion, telefono);
    }

    public Collection<AsignacionEntity> getAsignacionsById() {
        return asignacionsById;
    }

    public void setAsignacionsById(Collection<AsignacionEntity> asignacionsById) {
        this.asignacionsById = asignacionsById;
    }

    public Collection<ConversacionEntity> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<ConversacionEntity> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<SolicitudActivacionEntity> getSolicitudActivacionsById() {
        return solicitudActivacionsById;
    }

    public void setSolicitudActivacionsById(Collection<SolicitudActivacionEntity> solicitudActivacionsById) {
        this.solicitudActivacionsById = solicitudActivacionsById;
    }

    public Collection<SolicitudAltaEntity> getSolicitudAltasById() {
        return solicitudAltasById;
    }

    public void setSolicitudAltasById(Collection<SolicitudAltaEntity> solicitudAltasById) {
        this.solicitudAltasById = solicitudAltasById;
    }

    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(this.id);
        dto.setIdentificacion(this.identificacion);
        dto.setNombre(this.nombre);
        dto.setApellido(this.apellido);
        dto.setEmail(this.email);
        dto.setContrasena(this.contrasena);
        dto.setRol(this.rol);
        dto.setSubrol(this.subrol);
        dto.setDireccion(this.direccion);
        dto.setTelefono(this.telefono);
        dto.setAsignacionsById(this.asignacionsById);
        dto.setConversacionsById(this.conversacionsById);
        dto.setSolicitudActivacionsById(this.solicitudActivacionsById);
        dto.setSolicitudAltasById(this.solicitudAltasById);

        return dto;
    }
}
