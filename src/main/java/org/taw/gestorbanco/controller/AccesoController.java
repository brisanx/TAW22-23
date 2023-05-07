package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.Servicios.EmpleadoService;
import org.taw.gestorbanco.Servicios.UsuarioService;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;

import javax.servlet.http.HttpSession;

@Controller
public class AccesoController {

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected EmpleadoService empleadoService;

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
        return "loginempleado";
    }

    @PostMapping("/autenticar")
    public String doAutenticar (@RequestParam("usuario") String user,
                                @RequestParam("contrasena") String contrasena,
                                Model model, HttpSession session) {
        UsuarioDTO usuario = this.usuarioService.doAutenticarUsuario(user,contrasena);

        String urlTo = "";

        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "loginusuario";
        } else {
            session.setAttribute("user", usuario);
            urlTo = usuario.getRol().equalsIgnoreCase("Particular") ?
                    "redirect:/usuario/homeCliente" : "redirect:/empresa/paginaempresa";
        }


        return urlTo;
    }

    @PostMapping("/autenticarEmpleado")
    public String doAutenticarEmpelado (@RequestParam("usuario") String user,
                                @RequestParam("contrasena") String contrasena,
                                Model model, HttpSession session) {
        EmpleadoDTO empleado = this.empleadoService.doAutenticarEmpleado(user,contrasena);
        String urlTo = "";

        if (empleado == null) {
            System.out.println("Incorrecto");
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "loginempleado";
        } else {
            session.setAttribute("user", empleado);
            if(empleado.getRol().equalsIgnoreCase("asistente")) {
                urlTo = "redirect:/usuario/homeCliente";
            } else if (empleado.getRol().equalsIgnoreCase("gestor")) {
                urlTo = "redirect:/gestoria/inicio";
            } else {
                urlTo = "redirector/cajero";
            }
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
