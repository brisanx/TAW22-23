package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;
}
