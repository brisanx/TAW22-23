package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.service.AsignacionService;
import org.taw.gestorbanco.service.CuentaBancariaService;
import org.taw.gestorbanco.service.UsuarioService;

import javax.servlet.http.HttpSession;

/**
 * @author Jose Torres
 */

@Controller
public class UsuarioController {
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected CuentaBancariaService cuentaBancariaService;

    @Autowired
    protected AsignacionService asignacionService;

    @GetMapping("/registrocliente")
    public String doRegistroCliente(Model model) {
        UsuarioDTO cliente = new UsuarioDTO();
        model.addAttribute("cliente", cliente);
        return "registrocliente";
    }

    @PostMapping("/guardarcliente")
    public String doGuardarCliente(@ModelAttribute("cliente") UsuarioDTO usuario){
        this.usuarioService.guardarNuevoUsuario(usuario);
        return "pgCliente";
    }

    @GetMapping("/modificarDatos")
    public String doModificarDatos(Model model, HttpSession session){
        model.addAttribute("cliente", session.getAttribute("user"));
        return "modificarDatosParticulares";
    }

    @PostMapping("/guardarDatos")
    public String doGuardarDatos(@ModelAttribute("cliente")UsuarioDTO usuario, Model model, HttpSession session){
        this.usuarioService.modificarUsuario(usuario);
        model.addAttribute("cliente", usuario);
        session.setAttribute("user", usuario);
        return "pgCliente";
    }
}
