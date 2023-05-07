package org.taw.gestorbanco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    @Query("select u from UsuarioEntity u where u.email = :user and u.contrasena = :password")
    public UsuarioEntity autenticar (String user, String password);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%')")
    public List<UsuarioEntity> filtrarNombre (String nombre);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.apellido LIKE CONCAT('%', :nombre, '%')")
    public List<UsuarioEntity> filtrarApellido (String nombre);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%') AND" +
            " u.apellido LIKE CONCAT('%', :apellido, '%')")
    public List<UsuarioEntity> filtrarNombreApellido(String nombre, String apellido);

    @Query("SELECT u FROM UsuarioEntity u WHERE lower(u.rol) = lower(:tipo)")
    public List<UsuarioEntity> filtrarTipo(String tipo);
}
