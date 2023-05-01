package org.taw.gestorbanco.dto;

import org.taw.gestorbanco.entity.ConversacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.entity.SolicitudAltaEntity;

import java.util.Collection;

public class UsuarioDTO {
    private Integer id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String rol;
    private String subrol;
    private String direccion;
    private String telefono;
    private Collection<ConversacionEntity> conversacionsById;
    private Collection<CuentaBancariaEntity> cuentaBancariasById;
    private Collection<SolicitudActivacionEntity> solicitudActivacionsById;
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