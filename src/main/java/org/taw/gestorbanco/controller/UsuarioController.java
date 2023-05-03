package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.service.UsuarioService;

@Controller
public class UsuarioController {
    @Autowired
    protected UsuarioService usuarioService;

    @GetMapping("/registro")
    public String doRegistro(Model model){
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        return "empresa";
    }

    @GetMapping("/registrocliente")
    public String doRegistroCliente(Model model){
        UsuarioDTO dto = new UsuarioDTO();
        model.addAttribute("usuario", dto);
        return "userSignup";
    }

    @PostMapping("/registrar")
    public String doRegistroCliente(@ModelAttribute("usuario") UsuarioDTO usuario){
        this.usuarioService.doRegistro(usuario);
        return "redirect:/";
    }
}
