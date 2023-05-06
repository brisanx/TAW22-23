package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.service.UsuarioService;

import javax.servlet.http.HttpSession;

/**
 * @author Alba Sánchez Ibáñez (80%), José Torres Postigo (20%)
 */
@Controller
public class AccesoController {
    @Autowired
    protected UsuarioService usuarioService;
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
        UsuarioDTO usuario = this.usuarioService.doAutenticarUsuario(user,contrasena);

        String urlTo = "/";
        if(usuario!=null){
            session.setAttribute("user", usuario);
            urlTo = usuario.getRol().equalsIgnoreCase("Particular") ?
                    "redirect:/paginaCliente" : "redirect:/empresa/paginaempresa";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "loginusuario";
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}