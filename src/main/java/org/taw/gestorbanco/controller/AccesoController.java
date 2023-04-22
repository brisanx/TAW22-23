package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.repositories.UsuarioRepository;

import javax.servlet.http.HttpSession;

@Controller
public class AccesoController {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doInicio(){
        return "principal";
    }

    @GetMapping("/accesoUsuario")
    public String doAccesoUsuario(){
        return "usuario";
    }

    @GetMapping("/accesoEmpleado")
    public String doAccesoEmpleado(){
        return "empleado";
    }

    @PostMapping("/autenticar")
    public String doAutenticar (@RequestParam("usuario") String user,
                                @RequestParam("contrasena") String contrasena,
                                Model model, HttpSession session) {
        String urlTo = "redirect:/";
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user,contrasena);
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "usuario";
        } else {
            session.setAttribute("user", usuario);
        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
