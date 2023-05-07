package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taw.gestorbanco.Servicios.*;
import org.taw.gestorbanco.dto.*;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.filtros.clienteFiltro;
import org.taw.gestorbanco.filtros.crudSospechoso;
import org.taw.gestorbanco.filtros.opbFiltro;
import org.taw.gestorbanco.filtros.usuarioFiltro;
import org.taw.gestorbanco.repositories.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestoria")
public class GestorController {
    Byte cero = 0;
    Byte uno = 1;
    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected SolAltaService solAltaService;

    @Autowired
    protected SolActivacionService solActivacionService;

    @Autowired
    protected CuentaBancariaService cuentaBancariaService;

    @Autowired
    protected AsignacionService asignacionService;

    @Autowired
    protected OperacionBancariaService operacionBancariaService;


    @GetMapping("/inicio")
    public String doListar(Model model, HttpSession session){
        usuarioFiltro uF = new usuarioFiltro();
        clienteFiltro cF = new clienteFiltro();
        return  this.procesarFiltro(uF,cF, model, session);
    }

    @PostMapping("/filtrarNombres")
    public String doFiltrarN(@ModelAttribute("filtroN") usuarioFiltro usrF, Model model, HttpSession session){
        clienteFiltro cF = new clienteFiltro();
        return  this.procesarFiltro(usrF,cF, model, session);
    }

    @PostMapping("/filtrarTipo")
    public String doFiltrarT (@ModelAttribute("filtroT") clienteFiltro cF, Model model, HttpSession session){
        usuarioFiltro uF = new usuarioFiltro();
        return this.procesarFiltro(uF, cF, model, session);
    }

    public String procesarFiltro(usuarioFiltro usrF, clienteFiltro cF, Model model, HttpSession session){
        EmpleadoDTO empleado = (EmpleadoDTO) session.getAttribute("user");
        String url = "";
        System.out.println(empleado.getNombre());
        if(empleado == null){
            url = "principal";
        } else {
            List<UsuarioDTO> lista;
            System.out.println(cF.getTipo());
            if((usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo() == null) || (usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo().equals(""))){
                System.out.println("TODOS");
                lista = usuarioService.listarClientes();
            } else {
                lista = usuarioService.listarClientes(usrF.getNombre(), usrF.getApellido(), cF.getTipo());
            }
            model.addAttribute("filtroN", usrF);
            model.addAttribute("filtroT", cF);

            model.addAttribute("usuario", lista);

            List<SolicitudAltaDTO> solicitudes = solAltaService.doListar();
            model.addAttribute("pendientes", solicitudes);

            List<SolicitudActivacionDTO> activaciones = solActivacionService.doListar();
            model.addAttribute("activaciones", activaciones);

            Timestamp fechaHace30Dias = Timestamp.valueOf(LocalDateTime.now().minus(30, ChronoUnit.DAYS));

            List<CuentaBancariaDTO> menor30 = cuentaBancariaService.tienenQueBloquearse(fechaHace30Dias, uno);
            List<Timestamp> fechas = cuentaBancariaService.ultimaFechaOperacionBancaria(menor30);
            System.out.println("Cuentas " + menor30.size());
            System.out.println("Fechas "+ fechas.size());
            model.addAttribute("cDesactivar",menor30);
            model.addAttribute("fechas", fechas);

            url = "usuarios";
        }
        return url;
    }

    @GetMapping("/informacion")
    public String infoUsuario(@RequestParam("id") Integer id, Model model){
        opbFiltro filtro = new opbFiltro();
        return this.procesarFiltroOp(filtro, id, model);
    }

    @PostMapping("/filtrarOperacion")
    public String recibirFiltro(@ModelAttribute("filtro") opbFiltro filtro,  Model model){
        return  this.procesarFiltroOp(filtro, filtro.getId(), model);
    }

    public String procesarFiltroOp(@ModelAttribute("filtro") opbFiltro filtro, Integer userId, Model model){
        UsuarioDTO user = usuarioService.buscarUsuario(userId);
        if(filtro!=null) {
            System.out.println("Cantidad" + filtro.getCantidad());
            System.out.println("Fecha" + filtro.getFecha());
            System.out.println("MM" + filtro.getMm());
            System.out.println("MMc" + filtro.getMmc());

        }

        List<Integer> asignaciones = asignacionService.listaAsignacionesPorId(userId);
        List<OperacionBancariaDTO> operaciones = new ArrayList<>();

        try {
            if((filtro.getCantidad() == null && filtro.getFecha() == null) || filtro.getCantidad() == null && filtro.getFecha().isEmpty()) {
                filtro.setId(userId);
                operaciones = operacionBancariaService.operacionesPorAignaciones(asignaciones);
            } else  {
                operaciones = operacionBancariaService.operacionesPorFiltro(filtro, asignaciones);
            }
        } catch (NullPointerException e) {
            System.out.println("Error");
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("op", operaciones);
        model.addAttribute("cliente", user);
        return "usuarioInfo";
    }

    @GetMapping("/volverGestoria")
    public String volverGestoria(){
        return  "redirect:/gestoria/inicio";
    }

    @GetMapping("/aceptarAlta")
    public String doSolicitarAlta(@RequestParam("id") Integer id, @RequestParam("sol") Integer solID,
                                  Integer divisa){
        System.out.println(divisa);
        solAltaService.aceptarSolicitud(id, solID, divisa);
        solAltaService.eliminiarSolicitud(solID);

        return "redirect:/gestoria/inicio";
    }

    @GetMapping("/denegarAlta")
    public String doDenegarAlta(@RequestParam("sol") Integer solID){
        solAltaService.eliminiarSolicitud(solID);
        return "redirect:/gestoria/inicio";
    }

    @PostMapping("/sospechoso/agregar")
    public String agregarSospechoso(@ModelAttribute("agragar") crudSospechoso id){
        cuentaBancariaService.agregarCuentaSospechosa(id);
        return "redirect:/gestoria/sospechoso";
    }

    @PostMapping("/sospechoso/eliminar")
    public String elimiarSospechosos(@ModelAttribute("eliminar") crudSospechoso id){
        cuentaBancariaService.eliminarCuentaSospechosa(id);
        return "redirect:/gestoria/sospechoso";
    }

    @GetMapping("/sospechoso")
    public String mostrarSospechosos(Model model){
        List<CuentaBancariaDTO> cSospechosas = cuentaBancariaService.buscarCuentasSospechosas();
        System.out.println("Sospechosas cantidad "+cSospechosas.size());
        if(cSospechosas.isEmpty()) {
            List<CuentaBancariaDTO> comienzo = cuentaBancariaService.comienzoNoSospechosos();
            model.addAttribute("sospechosos", null);
            model.addAttribute("noSospechosos", comienzo);
        } else {
            List<CuentaBancariaDTO> noSospechosos = cuentaBancariaService.cuentasNoSospechosas();
            model.addAttribute("noSospechosos", noSospechosos);
            model.addAttribute("sospechosos", cSospechosas);
        }

        crudSospechoso filtroA = new crudSospechoso();
        crudSospechoso filtroE = new crudSospechoso();
        model.addAttribute("filtroA", filtroA);
        model.addAttribute("filtroE", filtroE);
        model.addAttribute("cSospechosas", cSospechosas);

        if(cSospechosas.isEmpty()){
            model.addAttribute("opSospechosas", null);
        } else{
            List<OperacionBancariaDTO> operaciones = operacionBancariaService.operacionesSospechosas();
            model.addAttribute("opSospechosas", operaciones);
        }

        return "sospechosos";
    }

    @GetMapping("/aceptarActivacion")
    public String doAceptarActivacion(@RequestParam("id") Integer id, @RequestParam("sol") Integer solID){
        cuentaBancariaService.aceptarActivacion(id, solID);

        return "redirect:/gestoria/inicio";
    }

    @GetMapping("/denegarActivacion")
    public String doDenegarActivacion(@RequestParam("sol") Integer solID){
        cuentaBancariaService.denegarActivacion(solID);
        return "redirect:/gestoria/inicio";
    }
    @GetMapping("/desactivarcuenta")
    public String desactivarCuenta(@RequestParam("idCuenta") Integer id){
        cuentaBancariaService.desactivarCuenta(id);

        return "redirect:/gestoria/inicio";
    }

    @GetMapping("/bloquear")
    public String bloquearPorFraude(@RequestParam("id")Integer id) {
        cuentaBancariaService.desactivarCuenta(id);

        return "redirect:/gestoria/sospechoso";
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session){
        session.invalidate();
        return "principal";
    }

}
