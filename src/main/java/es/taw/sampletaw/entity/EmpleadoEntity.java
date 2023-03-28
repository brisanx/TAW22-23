package es.taw.sampletaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "gestor_banco", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_gestor", nullable = false)
    private int idGestor;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "rol", nullable = false, length = 45)
    private String rol;

    public int getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(int idGestor) {
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
        return idGestor == that.idGestor && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestor, nombre, email, rol);
    }
}
