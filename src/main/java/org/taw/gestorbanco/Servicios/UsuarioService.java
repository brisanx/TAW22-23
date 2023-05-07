package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected AltaRepository altaRepository;

    @Autowired
    protected DivisasRepository divisasRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected ActivacionRepository activacionRepository;

    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;



    /*------------------------AUTENTICACION--------------------------------*/
    public UsuarioDTO doAutenticarUsuario(String user, String password){
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user, password);
        return (usuario == null ? null : usuario.toDto());
    }


    /*------------------------GESTOR--------------------------------*/
    public List<UsuarioDTO> listarClientes(){
    List<UsuarioEntity> lista = usuarioRepository.findAll();
    return this.conversion(lista);
    }

    public List<UsuarioDTO> listarClientes(String nombre, String apellido, String tipo) {
        List<UsuarioEntity> lista;
        if (nombre == null && apellido == null) {
            System.out.println("TIPOS");
            lista = usuarioRepository.filtrarTipo(tipo);
        } else if (tipo == null && nombre.equals("")) {
            System.out.println("APELLIDOS");
            lista = usuarioRepository.filtrarApellido(apellido);
        } else if (tipo == null && apellido.equals("")) {
            System.out.println("NOMBRES");
            lista = usuarioRepository.filtrarNombre(nombre);
        } else {
            System.out.println("APELLIDOSNOMBRE");
            System.out.println(nombre);
            System.out.println(apellido);
            lista = usuarioRepository.filtrarNombreApellido(nombre, apellido);
        }
        return this.conversion(lista);
    }

    public UsuarioDTO buscarUsuario(Integer id){
        UsuarioEntity usr = this.usuarioRepository.findById(id).orElse(null);
        return usr.toDto();
    }

    /*---------------------------------------USUARIO----------------------------*/

    public void guardarNuevoUsuario(UsuarioDTO dto){
        UsuarioEntity usuario;
        usuario = new UsuarioEntity();

        usuario.setId(dto.getId());
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena());
        usuario.setRol(dto.getRol());
        usuario.setSubrol(dto.getSubrol());
        usuario.setDireccion(dto.getDireccion());
        usuario.setTelefono(dto.getTelefono());

        this.usuarioRepository.save(usuario);

        /*-------------Crear Solicitud de alta-----------*/

    }

    public void modificarUsuario(UsuarioDTO dto){
        UsuarioEntity usuario = this.usuarioRepository.getById(dto.getId());

        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setDireccion(dto.getDireccion());
        usuario.setTelefono(dto.getTelefono());
        usuario.setContrasena(dto.getContrasena());

        this.usuarioRepository.save(usuario);
    }
    protected List<UsuarioDTO> conversion(List<UsuarioEntity> lista) {
        ArrayList usrs = new ArrayList<UsuarioDTO>();
        lista.forEach((final UsuarioEntity usuario) -> usrs.add(usuario.toDto()));
        return usrs;
    }




}
