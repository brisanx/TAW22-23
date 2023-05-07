package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.taw.gestorbanco.Servicios.*;
import org.taw.gestorbanco.dto.*;
import org.taw.gestorbanco.dto.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jose Torres
 */

/*@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected CuentaBancariaService cuentaBancariaService;

    @Autowired
    protected AsignacionService asignacionService;

    @Autowired
    protected OperacionBancariaService operacionBancariaService;

    @Autowired
    protected SolActivacionService solicitudActivacionService;

    @GetMapping("/homeCliente")
    public String doInit(Model model, HttpSession session){
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
        List<OperacionBancariaDTO> operaciones =
                this.operacionBancariaService.listarOperacionesClientes(usuario.getId());
        model.addAttribute(
                "cuenta", this.cuentaBancariaService.obtenerCuentaBancaria(usuario)
        );

        model.addAttribute("operaciones", operaciones);
        model.addAttribute("divisas", new DivisaDTO());
        return "pgCliente";
    }

    @GetMapping("/registrocliente")
    public String doRegistroCliente(Model model) {
        UsuarioDTO cliente = new UsuarioDTO();
        model.addAttribute("cliente", cliente);
        return "registrocliente";
    }

    @PostMapping("/guardarcliente")
    public String doGuardarCliente(@ModelAttribute("cliente") UsuarioDTO usuario) {
        this.usuarioService.guardarNuevoUsuario(usuario);
        return "redirect:/usuario/homeCliente";
    }

    @GetMapping("/modificarDatos")
    public String doModificarDatos(Model model, HttpSession session) {
        model.addAttribute("cliente", session.getAttribute("user"));
        return "modificarDatosParticulares";
    }

    @PostMapping("/guardarDatos")
    public String doGuardarDatos(@ModelAttribute("cliente") UsuarioDTO usuario, Model model, HttpSession session) {
        this.usuarioService.modificarUsuario(usuario);
        model.addAttribute("cliente", usuario);
        session.setAttribute("user", usuario);
        List<OperacionBancariaDTO> operaciones =
                this.operacionBancariaService.listarOperacionesClientes(usuario.getId());
        model.addAttribute("operaciones", operaciones);
        return "redirect:/usuario/homeCliente";
    }

    @GetMapping("/transferenciaCliente")
    public String doCargarTransferencia(Model model, HttpSession sesion) {
        OperacionBancariaDTO op = new OperacionBancariaDTO();
        model.addAttribute("operacion", op);

        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");

        CuentaBancariaDTO cuentaorigen = this.cuentaBancariaService.obtenerCuentaBancaria(usuario);

        model.addAttribute("cuentaorigen", cuentaorigen);
        return "trans";
    }

    @PostMapping("/transRealizada")
    public String doRealizarTransferencia(@ModelAttribute("operacion") OperacionBancariaDTO operacionOrigen,
                                          Model model, HttpSession session) {
        operacionOrigen.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        this.operacionBancariaService.guardarOperacionBancaria(operacionOrigen);
        this.cuentaBancariaService.ajustarSaldos(operacionOrigen);

        OperacionBancariaDTO operacionDestino;
        operacionDestino = new OperacionBancariaDTO();

        operacionDestino.setFecha(operacionOrigen.getFecha());
        operacionDestino.setCantidad(-operacionOrigen.getCantidad() * operacionOrigen.getCuentaBancariaByIdCuentaDestino().getDivisaByDivisaId().getRatioDeCambio());
        operacionDestino.setCuentaBancariaByIdCuentaDestino(operacionOrigen.getCuentaBancariaByIdCuentaOrigen());
        operacionDestino.setCuentaBancariaByIdCuentaOrigen(operacionOrigen.getCuentaBancariaByIdCuentaDestino());

        this.operacionBancariaService.guardarOperacionBancaria(operacionDestino);

        return "redirect:/usuario/homeCliente";
    }

    @GetMapping("/solicitarActivacion")
    public String doSolicitarActivacion(@ModelAttribute("cuenta") CuentaBancariaDTO cuenta, HttpSession session){
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
        this.solicitudActivacionService.registrarSolicitudActivacion(usuario, cuenta);

        return "redirect:/usuario/homeCliente";
    }

}*/