package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taw.gestorbanco.dto.*;
import org.taw.gestorbanco.filtros.opbFiltro;
import org.taw.gestorbanco.service.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    protected DivisaService divisaService;
    @Autowired
    protected EmpleadoService empleadoService;
    @Autowired
    protected SolicitudActivacionService solicitudActivacionService;

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
        List<CuentaBancariaDTO  > solicitudes = new ArrayList<>();
        List<CuentaBancariaDTO> cuentas = this.cuentaBancariaService.obtenerCuentasBancarias(usuario);
        model.addAttribute("user", usuario);
        model.addAttribute("cuentas", cuentas);
        for(CuentaBancariaDTO c:cuentas){
            if(this.solicitudActivacionService.buscarSolicitudActivacionPorUsuarioCuenta(usuario, c)!=null){
                solicitudes.add(this.solicitudActivacionService.buscarSolicitudActivacionPorUsuarioCuenta(usuario, c).getCuentaBancariaByCuentaBancariaId());
            }
        }
        model.addAttribute("solicitudes", solicitudes);
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
        List<CuentaBancariaDTO> origenes = this.cuentaBancariaService.obtenerCuentasBancarias(user);
        List<CuentaBancariaDTO> todasActivas = this.cuentaBancariaService.cuentasActivasTodas();
        List<CuentaBancariaDTO> activas = new ArrayList<>();
        for(CuentaBancariaDTO c:origenes){
            if(c.getActivo()==1){
                activas.add(c);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("operacion", op);
        model.addAttribute("origen", activas);
        model.addAttribute("todasActivas", todasActivas);
        return "transCajero";
    }

    @PostMapping("/confirmaTrans")
    public String guardarTransferencia(@ModelAttribute("operacion") OperacionBancariaDTO opOrigen){
        this.operacionBancariaService.guardarOperacionBancaria(opOrigen);
        this.cuentaBancariaService.ajustarSaldos(opOrigen);
        this.operacionBancariaService.guardarOperacionBancariaDestino(opOrigen);

        return "redirect:/cajero/miCuenta/";
    }

    @GetMapping("/sacarDinero")
    public String sacarDinero(Model model, HttpSession sesion){
        OperacionBancariaDTO op = new OperacionBancariaDTO();
        UsuarioDTO user = (UsuarioDTO) sesion.getAttribute("user");
        List<CuentaBancariaDTO> origenes = this.cuentaBancariaService.obtenerCuentasBancarias(user);
        List<CuentaBancariaDTO> activas = new ArrayList<>();
        for(CuentaBancariaDTO c:origenes){
            if(c.getActivo()==1){
                activas.add(c);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("operacion", op);
        model.addAttribute("origen", activas);
        return "sacarDinero";
    }

    @PostMapping("/confirmaSacar")
    public String confirmaSacarDinero(@ModelAttribute("operacion") OperacionBancariaDTO op){
        this.operacionBancariaService.guardarSacarDinero(op);
        this.cuentaBancariaService.restarDineroSacado(op);

        return "redirect:/cajero/miCuenta/";
    }

    @GetMapping("/listarOP")
    public String doListar(Model model, HttpSession sesion){
        UsuarioDTO user = (UsuarioDTO) sesion.getAttribute("user");
        opbFiltro filtro = null;
        return procesarFiltro(filtro, user.getId(), model);
    }

    public String procesarFiltro(opbFiltro filtro, Integer id, Model model){
        UsuarioDTO user = this.usuarioService.usuarioPorId(id);
        List<OperacionBancariaDTO> operaciones = this.operacionBancariaService.listarTodasOperacionesClientes(user, null);
        List<OperacionBancariaDTO> op = new ArrayList<>();
        if(filtro == null || (filtro.getCantidad() == null && filtro.getFecha() == null) || filtro.getCantidad() == null && filtro.getFecha().isEmpty()) {
            op = operaciones;
            filtro = new opbFiltro();
            filtro.setId(id);
        } else {
            op = operacionBancariaService.listarTodasOperacionesClientes(user, filtro);
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("ops", op);
        return "listarOpCajero";
    }

    @PostMapping("/filtrarOperacion")
    public String recibirFiltro(@ModelAttribute("filtro") opbFiltro filtro,  Model model){
        return  this.procesarFiltro(filtro, filtro.getId(), model);
    }

    @GetMapping("/cambiarDivisa")
    public String doDivsa(@RequestParam("cuenta_id")Integer cuenta_id,Model model){
        CuentaBancariaDTO cuenta = this.cuentaBancariaService.cuentaPorId(cuenta_id);
        List<DivisaDTO> divisas = this.divisaService.buscarTodasLasDivisas();
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("divisas", divisas);
        return "cambioDivisaCajero";

    }

    @PostMapping("/confirmaCambioDivisa")
    public String confirmaDivisa(@ModelAttribute("cuenta") CuentaBancariaDTO cuenta){
        this.cuentaBancariaService.cambiarDivisa(cuenta);

        return "redirect:/cajero/miCuenta/";
    }

    @GetMapping("/desbloqueo")
    public String solicitarDesbloqueo(@RequestParam("cuenta_id") Integer cuenta_id ,HttpSession sesion){
        UsuarioDTO usuarioActual = (UsuarioDTO) sesion.getAttribute("user");
        SolicitudActivacionDTO nuevaSolicitud = new SolicitudActivacionDTO();
        CuentaBancariaDTO cuenta = this.cuentaBancariaService.cuentaPorId(cuenta_id);
        EmpleadoDTO gestor = this.empleadoService.buscarGestor();

        nuevaSolicitud.setUsuarioByUsuarioId(usuarioActual);
        nuevaSolicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
        nuevaSolicitud.setCuentaBancariaByCuentaBancariaId(cuenta);
        nuevaSolicitud.setEmpleadoByEmpleadoIdGestor(gestor);
        this.solicitudActivacionService.guardarSolicitud(nuevaSolicitud);
        return "redirect:/cajero/miCuenta/";
    }

}
