package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.entity.UsuarioEntity;

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
        return "loginusuario";
    }

    @GetMapping("/accesoEmpleado")
    public String doAccesoEmpleado(){
        return "(inserteloqueseaaqui)";
    }

    @PostMapping("/autenticar")
    public String doAutenticar (@RequestParam("usuario") String user,
                                @RequestParam("contrasena") String contrasena,
                                Model model, HttpSession session) {
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user,contrasena);
        String urlTo = "redirect:/paginaempresa";
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "loginusuario";
        } else {
            session.setAttribute("user", usuario);
            if(usuario.getBloqueo()) urlTo="aviso";
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
