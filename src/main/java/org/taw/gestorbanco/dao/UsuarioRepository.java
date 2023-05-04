package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where u.email = :user and u.contrasena = :password")
    public UsuarioEntity autenticar (@Param("user") String user, @Param("password")String password);
    @Query("select u from UsuarioEntity u where u.identificacion = :id and u.subrol = ''")
    public UsuarioEntity buscarUsuarioEmpresaOriginal(@Param("id") String id);

    @Query("select u from UsuarioEntity u where u.rol = 'empresa' and u.subrol in ('socio', 'autorizado') and u.identificacion = :id")
    public List<UsuarioEntity> findEmpresaUsuariosSocioAutorizado(@Param("id") String id);
}
