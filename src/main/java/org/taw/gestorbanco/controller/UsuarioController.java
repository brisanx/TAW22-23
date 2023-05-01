package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.entity.UsuarioEntity;

@Controller
public class UsuarioController {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String doRegistro(Model model){
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        return "empresa";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("usuario") UsuarioEntity usuario, Model model){
        this.usuarioRepository.save(usuario);
        return "redirect:/";
    }
}
