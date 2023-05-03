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
}
