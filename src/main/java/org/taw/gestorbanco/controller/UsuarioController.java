package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taw.gestorbanco.dto.*;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.service.*;
import org.taw.gestorbanco.ui.opbFiltro;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jose Torres
 */

@Controller
public class UsuarioController {
    @Autowired
    protected UsuarioService usuarioService;
    @Autowired
    protected SolicitudAltaService solicitudAltaService;
    @Autowired
    protected DivisaService divisaService;
    @Autowired
    protected AsignacionService asignacionService;
    @Autowired
    protected CuentaBancariaService cuentaBancariaService;
    @Autowired
    protected SolicitudActivacionService solicitudActivacionService;
    @Autowired
    protected OperacionBancariaService operacionBancariaService;
    @Autowired
    protected EmpleadoService empleadoService;

    @GetMapping("/homeCliente")
    public String doInit(Model model, HttpSession session){
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
        List<CuentaBancariaDTO> cuentas = this.asignacionService.cuentasAsignadasAParticular(usuario.getId());

        model.addAttribute("cuentas", cuentas);
        return "pgCliente";
    }

    @GetMapping("/registrocliente")
    public String doRegistroCliente(Model model) {
        UsuarioDTO cliente = new UsuarioDTO();
        model.addAttribute("cliente", cliente);
        return "registrocliente";
    }

    @PostMapping("/guardarcliente")
    public String doGuardarCliente(@ModelAttribute("cliente") UsuarioDTO usuario, Model model, HttpSession session) {
        this.usuarioService.guardarUsuario(usuario);
        SolicitudAltaDTO solicitudAlta = new SolicitudAltaDTO();
        List<DivisaDTO> divisas = this.divisaService.buscarTodasLasDivisas();

        session.setAttribute("user", usuario);
        model.addAttribute("divisas", divisas);
        model.addAttribute("solicitud", solicitudAlta);
        return "solicitudAltaCliente";
    }

    @PostMapping("/solicitudAltaCliente")
    public String doSolicitarAltaCliente(@ModelAttribute("solicitud") SolicitudAltaDTO solicitud, HttpSession sesion){
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");

        EmpleadoDTO gestor = this.empleadoService.buscarGestor();
        solicitud.setEmpleadoByIdGestor(gestor);
        solicitud.setUsuarioByUsuarioId(usuario);
        this.solicitudAltaService.guardarSolicitudAlta(solicitud);

        usuario = this.usuarioService.ajustarId(usuario);
        sesion.setAttribute("user", usuario);

        return "redirect:/homeCliente";
    }

    @GetMapping("/modificarDatos")
    public String doModificarDatos(Model model, HttpSession session) {
        model.addAttribute("cliente", session.getAttribute("user"));
        return "modificarDatosParticulares";
    }

    @PostMapping("/guardarDatos")
    public String doGuardarDatos(@ModelAttribute("cliente") UsuarioDTO usuario, Model model, HttpSession session) {
        this.usuarioService.guardarUsuario(usuario);
        model.addAttribute("cliente", usuario);
        session.setAttribute("user", usuario);
        return "redirect:/homeCliente";
    }

    @GetMapping("/transferenciaCliente")
    public String doCargarTransferencia(@RequestParam("id") Integer id, Model model, HttpSession sesion) {
        // Creo la operación
        OperacionBancariaDTO operacion = new OperacionBancariaDTO();

        // Busco cuentaOrigen a través de la asignación que tiene personal con la cuenta de la empresa
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");
        CuentaBancariaDTO cuentaOrigen = this.cuentaBancariaService.buscarCuenta(id);

        // Añado al modelo
        model.addAttribute("usuario", usuario);
        model.addAttribute("operacion", operacion);
        model.addAttribute("cuentaorigen", cuentaOrigen);
        return "trans";
    }

    @PostMapping("/transRealizada")
    public String doRealizarTransferencia(@ModelAttribute("operacion") OperacionBancariaDTO operacionOrigen,
                                        Model model, HttpSession session) {
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");
        operacionOrigen.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        operacionOrigen.setUsuario(usuario);
        this.operacionBancariaService.guardarOperacionBancaria(operacionOrigen);
        this.cuentaBancariaService.ajustarSaldos(operacionOrigen);

        operacionOrigen = this.operacionBancariaService.setId(operacionOrigen);

        DivisaDTO divisa = this.divisaService.buscarDivisaOrigen(operacionOrigen);
        DivisaDTO divisaDestino = this.divisaService.buscarDivisaDestino(operacionOrigen);

        OperacionBancariaDTO operacionDestino = new OperacionBancariaDTO();
        operacionDestino.setUsuario(usuario);
        operacionDestino.setFecha(operacionOrigen.getFecha());

        double cantidad = -operacionOrigen.getCantidad();
        cantidad = cantidad/divisa.getRatioDeCambio();

        operacionDestino.setCantidad(cantidad * divisaDestino.getRatioDeCambio());
        operacionDestino.setCuentaBancariaByIdCuentaDestino(operacionOrigen.getCuentaBancariaByIdCuentaOrigen());
        operacionDestino.setCuentaBancariaByIdCuentaOrigen(operacionOrigen.getCuentaBancariaByIdCuentaDestino());

        this.operacionBancariaService.guardarOperacionBancaria(operacionDestino);

        return "redirect:/homeCliente";
    }

    @GetMapping("/solicitarActivacion")
    public String doSolicitarActivacion(@ModelAttribute("cuenta") CuentaBancariaDTO cuenta, HttpSession session){
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("user");

        EmpleadoDTO gestor = this.empleadoService.buscarGestor();

        SolicitudActivacionDTO nuevaSolicitud = new SolicitudActivacionDTO();
        nuevaSolicitud.setUsuarioByUsuarioId(usuario);
        nuevaSolicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
        nuevaSolicitud.setCuentaBancariaByCuentaBancariaId(cuenta);
        nuevaSolicitud.setEmpleadoByEmpleadoIdGestor(gestor);
        this.solicitudActivacionService.guardarSolicitud(nuevaSolicitud);

        return "redirect:/homeCliente";
    }

    @GetMapping("/cambiodivisa")
    public String doRealizarCambioDivisa(@RequestParam("id") Integer id, Model model){
        CuentaBancariaDTO cuenta = this.cuentaBancariaService.buscarCuenta(id);
        List<DivisaDTO> divisas = this.divisaService.buscarTodasLasDivisas();
        model.addAttribute("cuenta", cuenta);
        model.addAttribute("divisas", divisas);

        return "divisaCambioCliente";
    }

    @PostMapping("/guardarcambiodivisaCliente")
    public String doGuardarCambioDivisa(@ModelAttribute("cuenta") CuentaBancariaDTO cuenta){
        this.cuentaBancariaService.cambiarDivisa(cuenta);
        return "redirect:/homeCliente";
    }

    @GetMapping("/operaciones")
    public String doOperaciones(HttpSession sesion, Model model){
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");

        opbFiltro filtro = new opbFiltro();
        return procesarFiltro(filtro, usuario.getId(), model);
    }

    public String procesarFiltro(opbFiltro filtro, Integer id, Model model){
        List<Integer> asignaciones = asignacionService.listaAsignacionesPorId(id);

        List<OperacionBancariaDTO> op = null;
        if(filtro == null || (filtro.getCantidad() == null && filtro.getFecha() == null) || filtro.getCantidad() == null && filtro.getFecha().isEmpty()) {
            op = this.operacionBancariaService.listarOperacionesEmpresaPorAsignacion(asignaciones);
            filtro = new opbFiltro();
            filtro.setId(id);
        } else {
            op = operacionBancariaService.listarOperacionesClientes(asignaciones, filtro);
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("op", op);
        return "operacionesCliente";
    }

    @PostMapping("/filtrarOperacion")
    public String recibirFiltro(@ModelAttribute("filtro") opbFiltro filtro,  Model model){
        System.out.println("ID DE LA PERSONA: " + filtro.getId());
        return  this.procesarFiltro(filtro, filtro.getId(), model);
    }

    @GetMapping("/nuevaCuentaBancaria")
    public String doNuevaCuenta(HttpSession session, Model model){
        SolicitudAltaDTO solicitudAlta = new SolicitudAltaDTO();
        List<DivisaDTO> divisas = this.divisaService.buscarTodasLasDivisas();

        model.addAttribute("divisas", divisas);
        model.addAttribute("solicitud", solicitudAlta);
        return "solicitudAltaCliente";
    }
}
