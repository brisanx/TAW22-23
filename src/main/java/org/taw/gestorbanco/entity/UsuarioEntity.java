package org.taw.gestorbanco.entity;

import javax.persistence.*;
import java.util.Collection;

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
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<ConversacionEntity> conversacionsById;
    @OneToMany(mappedBy = "usuarioByUsuarioId")
    private Collection<CuentaBancariaEntity> cuentaBancariasById;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (identificacion != null ? !identificacion.equals(that.identificacion) : that.identificacion != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellido != null ? !apellido.equals(that.apellido) : that.apellido != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (contrasena != null ? !contrasena.equals(that.contrasena) : that.contrasena != null) return false;
        if (rol != null ? !rol.equals(that.rol) : that.rol != null) return false;
        if (subrol != null ? !subrol.equals(that.subrol) : that.subrol != null) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (identificacion != null ? identificacion.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        result = 31 * result + (rol != null ? rol.hashCode() : 0);
        result = 31 * result + (subrol != null ? subrol.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    public Collection<ConversacionEntity> getConversacionsById() {
        return conversacionsById;
    }

    public void setConversacionsById(Collection<ConversacionEntity> conversacionsById) {
        this.conversacionsById = conversacionsById;
    }

    public Collection<CuentaBancariaEntity> getCuentaBancariasById() {
        return cuentaBancariasById;
    }

    public void setCuentaBancariasById(Collection<CuentaBancariaEntity> cuentaBancariasById) {
        this.cuentaBancariasById = cuentaBancariasById;
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
}
