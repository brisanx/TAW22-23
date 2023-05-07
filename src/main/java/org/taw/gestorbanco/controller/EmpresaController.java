package org.taw.gestorbanco.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taw.gestorbanco.dao.*;
import org.taw.gestorbanco.dto.*;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.service.*;
import org.taw.gestorbanco.ui.opbFiltro;
import org.taw.gestorbanco.ui.subrolFiltro;
import org.taw.gestorbanco.ui.usuarioFiltro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Alba Sánchez Ibáñez 3ºIngeniería del Software A
 */
@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    protected UsuarioService usuarioService;
    @Autowired
    protected SolicitudAltaService solicitudAltaService;
    @Autowired
    protected EmpleadoService empleadoService;
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
    @GetMapping("/registro")
    public String doRegistroEmpresa(Model model) {
        // Creo empresa, la paso al modelo de registro
        UsuarioDTO empresa = new UsuarioDTO();
        model.addAttribute("empresa", empresa);

        return "registroempresa";
    }

    @PostMapping("/registrocompletado")
    public String doRegistroCompletadoEmpresa(@ModelAttribute("empresa") UsuarioDTO empresa, Model model, HttpSession sesion){
        // Guardo la empresa
        this.usuarioService.guardarNuevaEmpresa(empresa);

        //Creo alguien del personal de empresa
        UsuarioDTO personal = new UsuarioDTO();
        personal.setIdentificacion(empresa.getIdentificacion());

        //Modelo
        model.addAttribute("personal", personal);
        return "personal";
    }

    @PostMapping("/guardarpersonal")
    public String doRegistrarPersonal(@ModelAttribute("personal") UsuarioDTO personal, Model model,  HttpSession sesion){
        // Guardamos personal
        this.usuarioService.guardarPersonal(personal);
        sesion.setAttribute("user", personal);

        // Creo solicitud de alta de empresa (de socio primero). Guardo en una lista las divisas
        SolicitudAltaDTO solicitudEmpresa = new SolicitudAltaDTO();
        List<DivisaDTO> divisas = this.divisaService.buscarTodasLasDivisas();

        //Modelo
        model.addAttribute("divisas", divisas);
        model.addAttribute("solicitud", solicitudEmpresa);
        return "solicitudempresa";
    }

    @PostMapping("/solicitudalta")
    public String doSolicitarAltaEmpresa(@ModelAttribute("solicitud") SolicitudAltaDTO solicitud, HttpSession sesion){
        // Busco entre una lista de gestores el gestor que necesito
        UsuarioDTO personal = (UsuarioDTO) sesion.getAttribute("user");

        EmpleadoDTO gestor = this.empleadoService.buscarGestor();
        solicitud.setEmpleadoByIdGestor(gestor);
        solicitud.setUsuarioByUsuarioId(personal);
        this.solicitudAltaService.guardarSolicitudAlta(solicitud);

        personal = this.usuarioService.ajustarId(personal);
        sesion.setAttribute("user", personal);

        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/paginaempresa")
    public String doPaginaPrincipalEmpresa(Model model, HttpSession sesion){
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");
        if (usuario != null && usuario.getSubrol().equalsIgnoreCase("")){
            model.addAttribute("empresa", usuario);
            AsignacionDTO asignacionEmpresa = this.asignacionService.buscarAsignacion(usuario.getId());
            if(asignacionEmpresa == null) {
                UsuarioDTO primerSocio = this.usuarioService.buscarSocioOriginal(usuario.getIdentificacion());
                AsignacionDTO asignacionPrimerSocio = this.asignacionService.buscarAsignacion(primerSocio.getId());
                if(asignacionPrimerSocio==null){
                    model.addAttribute("asignacion", null);
                    return "pgempresa";
                }
                asignacionEmpresa = new AsignacionDTO();
                asignacionEmpresa.setUsuarioId(usuario.getId());
                asignacionEmpresa.setCuentaBancariaId(asignacionPrimerSocio.getCuentaBancariaId());
                this.asignacionService.guardarAsignacion(asignacionEmpresa);
            }
            model.addAttribute("asignacion", asignacionEmpresa);
            return "pgempresa";
        }
        else if(usuario!=null){
            AsignacionDTO asignacion = this.asignacionService.buscarAsignacion(usuario.getId());
            if(asignacion!=null){
                CuentaBancariaDTO cb = this.cuentaBancariaService.buscarCuenta(asignacion.getCuentaBancariaId());
                SolicitudActivacionDTO solicitudActivacion = this.solicitudActivacionService.buscarSolicitudActivacionPorUsuarioCuenta(usuario,cb);

                model.addAttribute("solicitudactivacion", solicitudActivacion);
                model.addAttribute("cuenta", cb);
            }

            model.addAttribute("asignacion", asignacion);
            model.addAttribute("usuariosocio", usuario);
            return "pgsocioautorizado";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/registrarpersonalextra")
    public String doRegistrarPersonalExtra(HttpServletRequest request, Model model, HttpSession sesion) {
        UsuarioDTO personal = new UsuarioDTO();
        String idEmpresa = request.getParameter("identificacion");

        model.addAttribute("idEmpresa", idEmpresa);
        model.addAttribute("personal", personal);
        return "personalextra";
    }

    @PostMapping("/registropersonalextrahecho")
    public String doRegistroPersonalExtraRealizado(@ModelAttribute("usuariosocio") UsuarioDTO usuarioSocio, Model model, HttpSession sesion) {
       this.usuarioService.guardarPersonal(usuarioSocio);
       usuarioSocio = this.usuarioService.ajustarId(usuarioSocio);

       UsuarioDTO empresa = (UsuarioDTO) sesion.getAttribute("user");
       AsignacionDTO asignacionEmpresa = this.asignacionService.buscarAsignacion(empresa.getId());

        AsignacionDTO asignacionPersonalExtra = new AsignacionDTO();
        asignacionPersonalExtra.setUsuarioId(usuarioSocio.getId());
        asignacionPersonalExtra.setCuentaBancariaId(asignacionEmpresa.getCuentaBancariaId());

        this.asignacionService.guardarAsignacion(asignacionPersonalExtra);
        return "redirect:/empresa/paginaempresa";
    }
    @PostMapping("/guardarcambiospersonal")
    public String doGuardarCambiosPersonal(@ModelAttribute("usuariosocio") UsuarioDTO usuarioSocio, Model model, HttpSession sesion){
        sesion.setAttribute("user", usuarioSocio);
        this.usuarioService.guardarPersonal(usuarioSocio);
        return "redirect:/empresa/paginaempresa";
    }
    @GetMapping("/cambiodatos")
    public String doCambiarCredencialesEmpresa(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        UsuarioDTO empresa = this.usuarioService.buscarEmpresa(id);

        model.addAttribute("usuario", empresa);
        return "cambiodatosempresa";
    }
    @PostMapping("/guardarcambiosempresa")
    public String doCambiarDatosEmpresa(@ModelAttribute("usuario") UsuarioDTO usuario, Model model) {
        this.usuarioService.guardarPersonal(usuario);
        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/bloquear")
    public String doBloquearUsuario (@RequestParam("id") Integer id, Model model){
        UsuarioDTO usuario = this.usuarioService.buscarPorId(id);

        this.asignacionService.eliminarAsignacion(usuario.getId());
        return "redirect:/empresa/paginaempresa";
    }
    @GetMapping("/solicitudactivacion")
    public String doRealizarSolicitudActivacion(@RequestParam("id") Integer id, HttpSession sesion){
        UsuarioDTO usuarioActual = (UsuarioDTO) sesion.getAttribute("user");
        CuentaBancariaDTO cuentaEmpresa = this.cuentaBancariaService.buscarCuenta(id);

        // Busco entre una lista de gestores el gestor que necesito
        EmpleadoDTO gestor = this.empleadoService.buscarGestor();

        //Creo SolicitudActivacion
       SolicitudActivacionDTO nuevaSolicitud = new SolicitudActivacionDTO();
       nuevaSolicitud.setUsuarioByUsuarioId(usuarioActual);
       nuevaSolicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
       nuevaSolicitud.setCuentaBancariaByCuentaBancariaId(cuentaEmpresa);
       nuevaSolicitud.setEmpleadoByEmpleadoIdGestor(gestor);
       this.solicitudActivacionService.guardarSolicitud(nuevaSolicitud);
        return "redirect:/empresa/paginaempresa";
    }
    @GetMapping("/transferencia")
    public String doRealizarTransferencia(@RequestParam("id") Integer id, Model model, HttpSession sesion)      {
        // Creo la operación
        OperacionBancariaDTO operacion = new OperacionBancariaDTO();

        // Busco cuentaOrigen a través de la asignación que tiene personal con la cuenta de la empresa
        UsuarioDTO personal = (UsuarioDTO) sesion.getAttribute("user");
        AsignacionDTO asignacionPersonal = this.asignacionService.buscarAsignacion(personal.getId());
        CuentaBancariaDTO cuentaOrigen = this.cuentaBancariaService.buscarCuenta(asignacionPersonal.getCuentaBancariaId());

        // Añado al modelo
        model.addAttribute("personal", personal);
        model.addAttribute("operacion", operacion);
        model.addAttribute("cuentaorigen", cuentaOrigen);
        return "trans";
    }

    @PostMapping("/transhecha")
    public String doGuardarTransferencia(@ModelAttribute("op") OperacionBancariaDTO operacionOrigen, Model model, HttpSession sesion) {
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");
        operacionOrigen.setUsuario(usuario);
        operacionOrigen.setFecha(Timestamp.valueOf(LocalDateTime.now()));

        this.operacionBancariaService.guardarOperacionBancaria(operacionOrigen);
        this.cuentaBancariaService.ajustarSaldos(operacionOrigen);
        operacionOrigen = this.operacionBancariaService.setId(operacionOrigen);

        DivisaDTO divisa = this.divisaService.buscarDivisaOrigen(operacionOrigen);
        DivisaDTO divisaDestino = this.divisaService.buscarDivisaDestino(operacionOrigen);

        OperacionBancariaDTO operacionDestino = new OperacionBancariaDTO();
        operacionDestino.setUsuario(usuario);
        operacionDestino.setFecha(operacionOrigen.getFecha());

        Double cantidad = -operacionOrigen.getCantidad();
        cantidad = cantidad/divisa.getRatioDeCambio();

        operacionDestino.setCantidad(cantidad * divisaDestino.getRatioDeCambio());
        operacionDestino.setCuentaBancariaByIdCuentaDestino(operacionOrigen.getCuentaBancariaByIdCuentaOrigen());
        operacionDestino.setCuentaBancariaByIdCuentaOrigen(operacionOrigen.getCuentaBancariaByIdCuentaDestino());

        this.operacionBancariaService.guardarOperacionBancaria(operacionDestino);
        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/cambiodivisa")
    public String doRealizarCambioDivisa(@RequestParam("id") Integer id, Model model){
        CuentaBancariaDTO cuentaEmpresa = this.cuentaBancariaService.buscarCuenta(id);
        List<DivisaDTO> divisas = this.divisaService.buscarTodasLasDivisas();
        model.addAttribute("cuentaempresa", cuentaEmpresa);
        model.addAttribute("divisas", divisas);

        return "divisacambio";
    }

    @PostMapping("/guardarcambiodivisa")
    public String doGuardarCambioDivisa(@ModelAttribute("cuentaempresa") CuentaBancariaDTO cuentaempresa){
       this.cuentaBancariaService.cambiarDivisa(cuentaempresa);
        return "redirect:/empresa/paginaempresa";
    }


    @PostMapping("/filtrarNombre")
    public String doFiltrarN(@ModelAttribute("filtroNombre") usuarioFiltro uF, Model model, HttpSession sesion){
        subrolFiltro cF = new subrolFiltro();
        UsuarioDTO user = (UsuarioDTO) sesion.getAttribute("user");
        return  this.procesarFiltro2(uF,cF, model, user);
    }

    @PostMapping("/filtrarTipo")
    public String doFiltrarT (@ModelAttribute("filtroTipo") subrolFiltro cF, Model model, HttpSession sesion){
        usuarioFiltro uF = new usuarioFiltro();
        UsuarioDTO user = (UsuarioDTO) sesion.getAttribute("user");
        return this.procesarFiltro2(uF, cF, model, user);
    }
    @GetMapping("/personal")
    public String doPersonal(HttpSession sesion, Model model){
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");
        usuarioFiltro uF = new usuarioFiltro();
        subrolFiltro cF = new subrolFiltro();
        return  this.procesarFiltro2(uF,cF, model,usuario);
    }
    public String procesarFiltro2(usuarioFiltro usrF, subrolFiltro cF, Model model, UsuarioDTO user){
        List<UsuarioDTO> lista;

        if((usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo() == null) || (usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo().equals(""))){
         lista = this.usuarioService.listarEmpresaUsuariosSocioAutorizado(user.getIdentificacion());
        } else {
            lista = this.usuarioService.listarPersonal(cF, usrF, user.getIdentificacion());
        }

        UsuarioDTO empresa = this.usuarioService.buscarEmpresa(user.getIdentificacion());
        AsignacionDTO asignacionEmpresa = this.asignacionService.buscarAsignacion(empresa.getId());
        List<AsignacionDTO> asignacionesEmpresa = this.asignacionService.buscarAsignacionesDeLaEmpresa(asignacionEmpresa.getCuentaBancariaId());

        List<Integer> usuariosAsignados = new ArrayList<>();
        for(AsignacionDTO asi : asignacionesEmpresa){
            usuariosAsignados.add(asi.getUsuarioId());
        }
        
        model.addAttribute("filtroNombre", usrF);
        model.addAttribute("filtroTipo", cF);
        model.addAttribute("usuario",user);
        model.addAttribute("asignaciones", usuariosAsignados);
        model.addAttribute("personalempresa", lista);

        return "listarpersonal";
    }
    @GetMapping("/operaciones")
    public String doOperaciones(HttpSession sesion, Model model){
        UsuarioDTO usuario = (UsuarioDTO) sesion.getAttribute("user");

        opbFiltro filtro = new opbFiltro();
        return procesarFiltro(filtro, usuario.getId(), model);
    }

    public String procesarFiltro(opbFiltro filtro, Integer id, Model model){
        List<Integer> asignaciones = this.asignacionService.todaslascuentasAsignadasPorUsuario(id);
        AsignacionDTO asignacion = this.asignacionService.buscarAsignacion(id);
        CuentaBancariaDTO cb = this.cuentaBancariaService.buscarCuenta(asignacion.getCuentaBancariaId());

        List<OperacionBancariaDTO> operaciones = this.operacionBancariaService.listarOperacionesEmpresa(cb.getId());
        List<OperacionBancariaDTO> op = null;
        if(filtro == null || (filtro.getCantidad() == null && filtro.getFecha() == null) || filtro.getCantidad() == null && filtro.getFecha().isEmpty()) {
            op = this.operacionBancariaService.listarOperacionesEmpresaPorAsignacion(asignaciones);
            filtro = new opbFiltro();
            filtro.setId(id);
        } else {
                op = operacionBancariaService.listarOperaciones(cb.getId(), filtro);
            }

        model.addAttribute("filtro", filtro);
        model.addAttribute("op", op);
        return "operaciones";
    }

    @PostMapping("/filtrarOperacion")
    public String recibirFiltro(@ModelAttribute("filtro") opbFiltro filtro,  Model model){
        System.out.println("ID DE LA PERSONA: " + filtro.getId());
        return  this.procesarFiltro(filtro, filtro.getId(), model);
    }

}
