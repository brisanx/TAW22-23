package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.filtros.clienteFiltro;
import org.taw.gestorbanco.filtros.crudSospechoso;
import org.taw.gestorbanco.filtros.opbFiltro;
import org.taw.gestorbanco.filtros.usuarioFiltro;
import org.taw.gestorbanco.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GestorController {
    Byte cero = 0;
    Byte uno = 1;
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected AltaRepository altaRepository;

    @Autowired
    protected DivisasRepository divisasRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected ActivacionRepository activacionRepository;

    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;


    @GetMapping("/gestoria")
    public String doListar(Model model){
        usuarioFiltro uF = new usuarioFiltro();
        clienteFiltro cF = new clienteFiltro();
        return  this.procesarFiltro(uF,cF, model);
    }

    @PostMapping("/filtrarNombres")
    public String doFiltrarN(@ModelAttribute("filtroN") usuarioFiltro usrF, Model model){
        clienteFiltro cF = new clienteFiltro();
        return  this.procesarFiltro(usrF,cF, model);
    }

    @PostMapping("/filtrarTipo")
    public String doFiltrarT (@ModelAttribute("filtroT") clienteFiltro cF, Model model){
        usuarioFiltro uF = new usuarioFiltro();
        return this.procesarFiltro(uF, cF, model);
    }

    public String procesarFiltro(usuarioFiltro usrF, clienteFiltro cF, Model model){
        List<UsuarioEntity> lista;
        System.out.println(cF.getTipo());
        if((usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo() == null) || (usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo().equals(""))){
            System.out.println("TODOS");
            lista = usuarioRepository.findAll();
        } else if (usrF == null || (usrF.getNombre() == null && usrF.getApellido() == null)) {
            System.out.println("TIPOS");
            lista = usuarioRepository.filtrarTipo(cF.getTipo());
        } else if (cF.getTipo() == null && usrF.getNombre().equals("")) {
            System.out.println("APELLIDOS");
            lista = usuarioRepository.filtrarApellido(usrF.getApellido());
        } else if (cF.getTipo() == null && usrF.getApellido().equals("")) {
            System.out.println("NOMBRES");
            lista = usuarioRepository.filtrarNombre(usrF.getNombre());
        } else {
            System.out.println("APELLIDOSNOMBRE");
            System.out.println(usrF.getNombre());
            System.out.println(usrF.getApellido());
            lista = usuarioRepository.filtrarNombreApellido(usrF.getNombre(), usrF.getApellido());
        }
        model.addAttribute("filtroN", usrF);
        model.addAttribute("filtroT", cF);

        model.addAttribute("usuario", lista);

        List<SolicitudAltaEntity> solicitudes = altaRepository.findAll();
        model.addAttribute("pendientes", solicitudes);

        List<SolicitudActivacionEntity> activaciones = activacionRepository.findAll();
        model.addAttribute("activaciones", activaciones);

        Timestamp fechaHace30Dias = Timestamp.valueOf(LocalDateTime.now().minus(30, ChronoUnit.DAYS));

        List<CuentaBancariaEntity> menor30 = cuentaBancariaRepository.filtroFecha(fechaHace30Dias, uno);
        List<Timestamp> fechas = cuentaBancariaRepository.verFechas(menor30);
        model.addAttribute("cDesactivar",menor30);
        model.addAttribute("fechas", fechas);

        return "usuarios";
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
        UsuarioEntity user = usuarioRepository.findById(userId).orElse(null);
        if(filtro!=null) {
            System.out.println("Cantidad" + filtro.getCantidad());
            System.out.println("Fecha" + filtro.getFecha());
            System.out.println("MM" + filtro.getMm());
            System.out.println("MMc" + filtro.getMmc());

        }

        List<Integer> asignaciones = asignacionRepository.cuentasAsignadasPorUsuario(userId);
        List<OperacionBancariaEntity> operaciones = operacionBancariaRepository.operacionesPorAsignacion(asignaciones);
        List<OperacionBancariaEntity> op = new ArrayList<>();
        if((filtro.getCantidad() == null && filtro.getFecha() == null) || filtro.getCantidad() == null && filtro.getFecha().isEmpty()) {
            filtro.setId(userId);
            op = operacionBancariaRepository.operacionesPorAsignacion(asignaciones);
        } else if (filtro.getCantidad() == null) {
            System.out.println("1");
            if(filtro.getMm()) {
                op = operacionBancariaRepository.opsMenorFecha(operaciones, filtro.conversion());
            } else{
                System.out.println("1-2    " + filtro.conversion());
                op = operacionBancariaRepository.opsMayorFecha(operaciones, filtro.conversion());
            }
        } else if (filtro.getFecha().isEmpty()) {
            System.out.println("2");
            if(filtro.getMmc()){
                op = operacionBancariaRepository.opsMenorCantidad(operaciones, filtro.getCantidad());
            } else {
                op = operacionBancariaRepository.opsMayorCantidad(operaciones, filtro.getCantidad());
            }
        } else {
            System.out.println("3");
            if(filtro.getMm() && filtro.getMmc()) {
                op = operacionBancariaRepository.opsMeMe(operaciones, filtro.getCantidad(), filtro.conversion());
            } else if (!filtro.getMm() && filtro.getMmc()) {
                op = operacionBancariaRepository.opsMaMe(operaciones, filtro.getCantidad(), filtro.conversion());
            } else if (filtro.getMm() && !filtro.getMmc()) {
                op = operacionBancariaRepository.opsMeMa(operaciones, filtro.getCantidad(), filtro.conversion());
            } else {
                op = operacionBancariaRepository.opsMaMa(operaciones, filtro.getCantidad(), filtro.conversion());
            }
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("op", op);
        model.addAttribute("cliente", user);
        return "usuarioInfo";
    }

    @GetMapping("/volverGestoria")
    public String volverGestoria(){
        return  "redirect:/gestoria";
    }

    @GetMapping("/aceptarAlta")
    public String doSolicitarAlta(@RequestParam("id") Integer id, @RequestParam("sol") Integer solID,
                                  Integer divisa){
        System.out.println(divisa);
        DivisaEntity divisaSol = divisasRepository.findById(divisa).orElse(null);

        CuentaBancariaEntity cuentaNueva = new CuentaBancariaEntity();

        cuentaNueva.setSaldo(0.0);
        cuentaNueva.setMoneda(divisaSol.getNombre());
        cuentaNueva.setSospechosa(cero);
        cuentaNueva.setActivo(uno);
        cuentaNueva.setDivisaByDivisaId(divisaSol);

        cuentaBancariaRepository.save(cuentaNueva);
        CuentaBancariaEntity laNueva = cuentaBancariaRepository.ultimaCuenta();

        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(id);
        asignacion.setCuentaBancariaId(laNueva.getId());
        System.out.println(asignacion.getUsuarioId().getClass() + "---" + asignacion.getCuentaBancariaId().getClass());
        asignacionRepository.save(asignacion);

        altaRepository.deleteById(solID);

        return "redirect:/gestoria";
    }

    @GetMapping("/denegarAlta")
    public String doDenegarAlta(@RequestParam("sol") Integer solID){
        altaRepository.deleteById(solID);
        return "redirect:/gestoria";
    }

    @PostMapping("/sospechoso/agregar")
    public String agregarSospechoso(@ModelAttribute("agragar") crudSospechoso id){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id.getId()).orElse(null);
        cb.setSospechosa(uno);
        cuentaBancariaRepository.save(cb);

        return "redirect:/sospechoso";
    }

    @PostMapping("/sospechoso/eliminar")
    public String elimiarSospechosos(@ModelAttribute("eliminar") crudSospechoso id){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id.getId()).orElse(null);
        cb.setSospechosa(cero);
        cuentaBancariaRepository.save(cb);
        return "redirect:/sospechoso";
    }

    @GetMapping("/sospechoso")
    public String mostrarSospechosos(Model model){
        List<CuentaBancariaEntity> cSospechosas = cuentaBancariaRepository.cuentasSospechosas(uno);
        System.out.println("Sospechosas cantidad "+cSospechosas.size());
        if(cSospechosas.isEmpty()) {
            //System.out.println("HAY Sospechosas");
            List<CuentaBancariaEntity> comienzo = cuentaBancariaRepository.findAll();
            model.addAttribute("sospechosos", null);
            model.addAttribute("noSospechosos", comienzo);
        } else {
            List<CuentaBancariaEntity> noSospechosos = cuentaBancariaRepository.noSospechosos(cSospechosas);
            model.addAttribute("noSospechosos", noSospechosos);
            model.addAttribute("sospechosos", cSospechosas);
        }
        crudSospechoso filtroA = new crudSospechoso();
        crudSospechoso filtroE = new crudSospechoso();
        model.addAttribute("filtroA", filtroA);
        model.addAttribute("filtroE", filtroE);
        model.addAttribute("cSospechosas", cSospechosas);

        if(cSospechosas.isEmpty()){
            model.addAttribute("posSospechoso", null);
            model.addAttribute("opSospechosas", null);
        } else{
            List<CuentaBancariaEntity> cuentasCuidado = cuentaBancariaRepository.encontrarTransferenciasSospechosas(cSospechosas, uno);
            System.out.println("Cuentas "+ cuentasCuidado.size());
            List<OperacionBancariaEntity> operaciones = operacionBancariaRepository.ultimasOperacionesSospechosas(cSospechosas, cuentasCuidado);
            System.out.println("fechas "+ operaciones.size());
            model.addAttribute("posSospechoso", cuentasCuidado);
            model.addAttribute("opSospechosas", operaciones);
        }


        return "sospechosos";
    }

    @GetMapping("/aceptarActivacion")
    public String doAceptarActivacion(@RequestParam("id") Integer id, @RequestParam("sol") Integer solID){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id).orElse(null);
        cb.setActivo(uno);

        OperacionBancariaEntity op = new OperacionBancariaEntity();
        op.setCantidad(0.0);
        op.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        op.setCuentaBancariaByIdCuentaDestino(cb);
        op.setCuentaBancariaByIdCuentaOrigen(cb);

        List<OperacionBancariaEntity> ops = (List<OperacionBancariaEntity>) cb.getOperacionBancariasById();
        ops.add(op);
        cb.setOperacionBancariasById(ops);

        cuentaBancariaRepository.save(cb);
        operacionBancariaRepository.save(op);
        activacionRepository.deleteById(solID);

        return "redirect:/gestoria#solActivacion";
    }

    @GetMapping("/denegarActivacion")
    public String doDenegarActivacion(@RequestParam("sol") Integer solID){
        activacionRepository.deleteById(solID);
        return "redirect:/gestoria#solActivacion";
    }
    @GetMapping("/desactivarcuenta")
    public String desactivarCuenta(@RequestParam("idCuenta") Integer id){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id).orElse(null);
        cb.setActivo(cero);
        cuentaBancariaRepository.save(cb);

        return "redirect:/gestoria";
    }

    @GetMapping("/bloquear")
    public String bloquearPorFraude(@RequestParam("id")Integer id) {
        CuentaBancariaEntity cuenta = cuentaBancariaRepository.findById(id).orElse(null);
        cuenta.setActivo(cero);
        cuentaBancariaRepository.save(cuenta);

        return "redirect:/sospechoso";
    }

}
