package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import javax.servlet.http.HttpSession;

/**
 * @author Alba Sánchez Ibáñez, José Torres Postigo 3ºIngeniería del Software
 */
@Controller
public class AccesoController {
    @Autowired
    protected UsuarioRepository usuarioRepository;
    @Autowired
    protected AsignacionRepository asignacionRepository;

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
        String urlTo = "/";
        if(usuario!=null){
            urlTo = usuario.getRol().equalsIgnoreCase("Particular") ?
                    "redirect:/paginaCliente" : "redirect:/empresa/paginaempresa";
        }
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "loginusuario";
        } else {
            session.setAttribute("user", usuario);
            AsignacionEntity asi = this.asignacionRepository.findByUsuarioIdEmpresa(usuario.getId());
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}