package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.UsuarioEntity;

@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public UsuarioDTO doAutenticarUsuario(String user, String password){
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user, password);
        return (usuario == null ? null : usuario.toDTO());
    }

    public void doRegistro(UsuarioDTO dto){
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
        usuario.setAsignacionsById(dto.getAsignacionsById());
        usuario.setConversacionsById(dto.getConversacionsById());
        usuario.setCuentaBancariasById(dto.getCuentaBancariasById());
        usuario.setSolicitudActivacionsById(dto.getSolicitudActivacionsById());
        usuario.setSolicitudAltasById(dto.getSolicitudAltasById());

        this.usuarioRepository.save(usuario);
    }
}
