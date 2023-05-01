package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where u.email = :user and u.contrasena = :password")
    UsuarioEntity autenticar(String user, String password);
}
