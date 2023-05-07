package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.util.List;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo, Óscar Hidalgo Puertas
 */
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where u.email = :user and u.contrasena = :password")
    public UsuarioEntity autenticar (@Param("user") String user, @Param("password")String password);
    @Query("select u from UsuarioEntity u where u.identificacion = :id and (u.subrol = '' or u.subrol is null)")
    public UsuarioEntity buscarUsuarioEmpresaOriginal(@Param("id") String id);


    @Query("select u from UsuarioEntity u where u.rol = 'empresa' and u.subrol in ('socio', 'autorizado') and u.identificacion = :id")
    public List<UsuarioEntity> findEmpresaUsuariosSocioAutorizado(@Param("id") String id);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%')")
    public List<UsuarioEntity> filtrarNombre (String nombre);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.apellido LIKE CONCAT('%', :nombre, '%')")
    public List<UsuarioEntity> filtrarApellido (String nombre);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%') AND" +
            " u.apellido LIKE CONCAT('%', :apellido, '%')")
    public List<UsuarioEntity> filtrarNombreApellido(String nombre, String apellido);
    @Query("SELECT u FROM UsuarioEntity u WHERE lower(u.rol) = lower(:tipo)")
    public List<UsuarioEntity> filtrarTipo(String tipo);

    @Query("SELECT u FROM UsuarioEntity u WHERE lower(u.subrol) = lower(:tipo) and u.identificacion = :identificacion")
    List<UsuarioEntity> filtrarTipoSubrolEmpresa(String tipo, String identificacion);
    @Query("SELECT u FROM UsuarioEntity u  WHERE u.apellido LIKE CONCAT('%', :apellido, '%') and u.identificacion = :identificacion")
    List<UsuarioEntity> filtrarApellidoEmpresa(String apellido, String identificacion);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%') and u.identificacion = :identificacion")
    List<UsuarioEntity> filtrarNombreEmpresa(String nombre, String identificacion);
    @Query("SELECT u FROM UsuarioEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%') AND u.apellido LIKE CONCAT('%', :apellido, '%') and u.identificacion = :identificacion")
    List<UsuarioEntity> filtrarNombreApellidoEmpresa(String nombre, String apellido, String identificacion);

    @Query("select u from UsuarioEntity u where u.identificacion = :identificacion and u.subrol = 'socio'")
    UsuarioEntity buscarSocioOriginal(String identificacion);

    @Query("select u from UsuarioEntity  u where u.email = :email")
    UsuarioEntity findByEmail(String email);
}
