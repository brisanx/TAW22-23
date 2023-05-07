package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.service.AsignacionService;
import org.taw.gestorbanco.service.CuentaBancariaService;
import org.taw.gestorbanco.service.UsuarioService;

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
        UsuarioDTO usuario = new UsuarioDTO();
        model.addAttribute("usuario", usuario);
        return "registrocliente";
    }

    @PostMapping("/guardarcliente")
    public String doGuardarCliente(@ModelAttribute("usuario") UsuarioDTO usuario){
      //  this.usuarioService.guardarUsuario(usuario);
        return "homeCliente";
    }
}
