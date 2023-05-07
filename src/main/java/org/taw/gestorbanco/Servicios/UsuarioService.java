package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.filtros.subrolFiltro;
import org.taw.gestorbanco.filtros.usuarioFiltro;
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

    /*----------------------------------EMPRESA----------------------------------------*/

    public void guardarPersonal(UsuarioDTO dto) {
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
    }

    public UsuarioDTO ajustarId(UsuarioDTO dto) {
        UsuarioEntity personal = this.usuarioRepository.findByEmail(dto.getEmail());
        personal.setId(personal.getId());
        return personal.toDto();
    }

    public UsuarioDTO buscarSocioOriginal(String identificacion) {
        UsuarioEntity primerSocio = this.usuarioRepository.buscarSocioOriginal(identificacion);
        return primerSocio.toDto();
    }

    public UsuarioDTO buscarEmpresa(String id) {
        UsuarioEntity empresa = this.usuarioRepository.buscarUsuarioEmpresaOriginal(id);
        return empresa.toDto();
    }

    public UsuarioDTO buscarPorId(Integer id) {
        UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario.toDto();
    }

    public List<UsuarioDTO> listarEmpresaUsuariosSocioAutorizado(String identificacion) {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findEmpresaUsuariosSocioAutorizado(identificacion);
        return this.listaEntidadesADTO(usuarios);
    }
    protected List<UsuarioDTO> listaEntidadesADTO(List<UsuarioEntity> lista) {
        ArrayList dtos = new ArrayList<UsuarioDTO>();
        lista.forEach(UsuarioEntity -> dtos.add(UsuarioEntity.toDto()));
        return dtos;
    }

    public List<UsuarioDTO> listarPersonal(subrolFiltro cF, usuarioFiltro usrF, String identificacion) {
        List<UsuarioEntity> lista;
        if (usrF == null || (usrF.getNombre() == null && usrF.getApellido() == null)) {
            lista = usuarioRepository.filtrarTipoSubrolEmpresa(cF.getTipo(), identificacion);
        } else if (cF.getTipo() == null && usrF.getNombre().equals("")) {
            lista =  usuarioRepository.filtrarApellidoEmpresa(usrF.getApellido(), identificacion);
        } else if (cF.getTipo() == null && usrF.getApellido().equals("")) {
            lista =  usuarioRepository.filtrarNombreEmpresa(usrF.getNombre(), identificacion);
        } else {
            lista =  usuarioRepository.filtrarNombreApellidoEmpresa(usrF.getNombre(), usrF.getApellido(), identificacion);
        }

        return this.listaEntidadesADTO(lista);
    }

    public void guardarNuevaEmpresa(UsuarioDTO dto) {
        UsuarioEntity empresa = new UsuarioEntity();

        empresa.setId(dto.getId());
        empresa.setIdentificacion(dto.getIdentificacion());
        empresa.setNombre(dto.getNombre());
        empresa.setEmail(dto.getEmail());
        empresa.setContrasena(dto.getContrasena());
        empresa.setRol(dto.getRol());
        empresa.setDireccion(dto.getDireccion());
        empresa.setTelefono(dto.getTelefono());

        this.usuarioRepository.save(empresa);
    }







}
