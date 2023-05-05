package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.service.CuentaBancariaService;
import org.taw.gestorbanco.service.OperacionBancariaService;
import org.taw.gestorbanco.service.UsuarioService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cajero")
public class CajeroController {
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected CuentaBancariaService cuentaBancariaService;

    @Autowired
    protected OperacionBancariaService operacionBancariaService;

    @GetMapping("/")
    protected String inicio(){
        return "cajeroInicio";
    }

    @PostMapping("/iniciar")
    public String doAutenticar (@RequestParam("usuario") String user,
                                @RequestParam("contrasena") String contrasena,
                                Model model, HttpSession session) {
        String urlTo = "redirect:/cajero/miCuenta";
        UsuarioDTO usuario = this.usuarioService.doAutenticarUsuario(user,contrasena);

        if (usuario == null || !usuario.getRol().equalsIgnoreCase("Particular")) {
            model.addAttribute("error", "Credenciales incorrectas");
            urlTo = "cajeroInicio";
        } else {
            session.setAttribute("user", usuario);
        }
        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/cajero/";
    }

    @GetMapping("/miCuenta")
    public String mostrarInfo(HttpSession session, Model model){
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
        CuentaBancariaDTO cuenta = this.cuentaBancariaService.obtenerCuentaBancaria(usuario);
        model.addAttribute("user", usuario);
        model.addAttribute("cuenta", cuenta);
        return "cajeroPrincipal";
    }

    @GetMapping("/modificarDatos")
    public String doModificar(Model model, HttpSession session){
        model.addAttribute("cliente", session.getAttribute("user"));
        return "modificarDatosCajero";
    }

    @PostMapping("/guardarDatos")
    public String doGuardarDatos(@ModelAttribute("cliente") UsuarioDTO user, Model model, HttpSession session){
        this.usuarioService.modificarUsuario(user);
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        return "redirect:/cajero/miCuenta/";
    }

    @GetMapping("/transferenciaCajero")
    public String doTransferir(Model model, HttpSession sesion){
        OperacionBancariaDTO op = new OperacionBancariaDTO();
        UsuarioDTO user = (UsuarioDTO) sesion.getAttribute("user");
        CuentaBancariaDTO origen = this.cuentaBancariaService.obtenerCuentaBancaria(user);

        model.addAttribute("operacion", op);
        model.addAttribute("cOrigen", origen);
        return "transCajero";
    }

    @PostMapping("/confirmaTrans")
    public String guardarTransferencia(@ModelAttribute("operacion") OperacionBancariaDTO opOrigen){
        this.operacionBancariaService.guardarOperacionBancaria(opOrigen);
        this.cuentaBancariaService.ajustarSaldos(opOrigen);
        this.operacionBancariaService.guardarOperacionBancariaDestino(opOrigen);

        return "redirect:/cajero/miCuenta/";
    }
}
