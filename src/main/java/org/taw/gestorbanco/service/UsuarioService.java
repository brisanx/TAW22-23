package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.DivisaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.ui.subrolFiltro;
import org.taw.gestorbanco.ui.usuarioFiltro;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo
 */
@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public UsuarioDTO doAutenticarUsuario(String user, String password){
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user, password);
        return (usuario == null ? null : usuario.toDTO());
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
        return personal.toDTO();
    }

    public UsuarioDTO buscarSocioOriginal(String identificacion) {
        UsuarioEntity primerSocio = this.usuarioRepository.buscarSocioOriginal(identificacion);
        return primerSocio.toDTO();
    }

    public UsuarioDTO buscarEmpresa(String id) {
        UsuarioEntity empresa = this.usuarioRepository.buscarUsuarioEmpresaOriginal(id);
        return empresa.toDTO();
    }

    public UsuarioDTO buscarPorId(Integer id) {
        UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario.toDTO();
    }

    public List<UsuarioDTO> listarEmpresaUsuariosSocioAutorizado(String identificacion) {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findEmpresaUsuariosSocioAutorizado(identificacion);
        return this.listaEntidadesADTO(usuarios);
    }
    protected List<UsuarioDTO> listaEntidadesADTO(List<UsuarioEntity> lista) {
        ArrayList dtos = new ArrayList<UsuarioDTO>();
        lista.forEach(UsuarioEntity -> dtos.add(UsuarioEntity.toDTO()));
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
}
