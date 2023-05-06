package org.taw.gestorbanco.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.taw.gestorbanco.dao.*;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.filtros.opbFiltro;
import org.taw.gestorbanco.filtros.subrolFiltro;
import org.taw.gestorbanco.filtros.usuarioFiltro;

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
    protected UsuarioRepository usuarioRepository;
    @Autowired
    protected DivisaRepository divisaRepository;
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    protected AsignacionRepository asignacionRepository;
    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;
    @Autowired
    protected EmpleadoRepository empleadoRepository;
    @Autowired
    protected SolicitudAltaRepository solicitudAltaRepository;
    @Autowired
    protected SolicitudActivacionRepository solicitudActivacionRepository;
    @GetMapping("/registro")
    public String doRegistroEmpresa(Model model) {
        // Creo empresa, la paso al modelo de registro
        UsuarioEntity empresa = new UsuarioEntity();
        model.addAttribute("empresa", empresa);

        return "registroempresa";
    }

    @PostMapping("/registrocompletado")
    public String doRegistroCompletadoEmpresa(@ModelAttribute("empresa") UsuarioEntity empresa, Model model, HttpSession sesion) {
        // Guardo la empresa
        this.usuarioRepository.save(empresa);
        sesion.setAttribute("user", empresa);

        //Creo alguien del personal de empresa
        UsuarioEntity personal = new UsuarioEntity();
        personal.setIdentificacion(empresa.getIdentificacion());

        //Modelo
        model.addAttribute("personal", personal);
        return "personal";
    }
    @PostMapping("/registropersonal")
    public String doRegistrarPersonal(@ModelAttribute("personal") UsuarioEntity personal, Model model, HttpSession sesion) {
        // Guardamos personal
        this.usuarioRepository.save(personal);

        //Recojo empresa
        UsuarioEntity empresa = (UsuarioEntity) sesion.getAttribute("user");

        // Creo solicitud de alta de empresa (de socio primero). Guardo en una lista las divisas
        SolicitudAltaEntity solicitudEmpresa = new SolicitudAltaEntity();
        List<DivisaEntity> divisas = this.divisaRepository.findAll();

        // Busco entre una lista de gestores el gestor que necesito
        EmpleadoEntity gestor = buscarGestor();

        // Relleno la solicitud
        solicitudEmpresa.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
        solicitudEmpresa.setEmpleadoByIdGestor(gestor);
        solicitudEmpresa.setUsuarioByUsuarioId(personal);

        //Modelo
        model.addAttribute("divisas", divisas);
        model.addAttribute("solicitud", solicitudEmpresa);

        return "solicitudempresa";
    }
    @PostMapping("/solicitudalta")
    public String doSolicitarAltaEmpresa(@ModelAttribute("solicitud") SolicitudAltaEntity solicitud){
        //Guardo la solicitud
        this.solicitudAltaRepository.save(solicitud);

        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/registrarpersonalextra")
    public String doGuardarEmpresaGet(HttpServletRequest request, Model model) {

        UsuarioEntity personal = new UsuarioEntity();
        String idEmpresa = request.getParameter("identificacion");

        model.addAttribute("idEmpresa", idEmpresa);
        model.addAttribute("personal", personal);
        return "personalextra";
    }
    @PostMapping("/registropersonalextrahecho")
    public String doGuardarEmpresaGet(@ModelAttribute("personal") UsuarioEntity personal) {
        AsignacionEntity asignacionPersonal = new AsignacionEntity();
        asignacionPersonal.setUsuarioId(personal.getId());
        // asignacionPersonal.setCuentaBancariaId();
        this.usuarioRepository.save(personal);
        return "redirect:/empresa/paginaempresa";
    }
    @GetMapping("/cambiodatos")
    public String doCambiarCredencialesEmpresa(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        UsuarioEntity empresa= this.usuarioRepository.buscarUsuarioEmpresaOriginal(id);
        model.addAttribute("usuario", empresa);
        return "cambiodatosempresa";
    }

    @PostMapping("/guardarcambiosempresa")
    public String doCambiarDatosEmpresa(@ModelAttribute("usuario") UsuarioEntity usuario, Model model, HttpSession sesion) {
        this.usuarioRepository.save(usuario);
        return "redirect:/empresa/paginaempresa";
    }
    @PostMapping("/guardarcambiospersonal")
    public String doCambiarDatosSocio(@ModelAttribute("usuariosocio") UsuarioEntity usuarioSocio, Model model, HttpSession sesion) {
        UsuarioEntity existingUser = usuarioRepository.findById(usuarioSocio.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + usuarioSocio.getId()));

        existingUser.setNombre(usuarioSocio.getNombre());
        existingUser.setApellido(usuarioSocio.getApellido());
        existingUser.setEmail(usuarioSocio.getEmail());
        existingUser.setDireccion(usuarioSocio.getDireccion());
        existingUser.setTelefono(usuarioSocio.getTelefono());
        existingUser.setSubrol(usuarioSocio.getSubrol());
        existingUser.setContrasena(usuarioSocio.getContrasena());

        usuarioRepository.save(existingUser);
        sesion.setAttribute("user", existingUser);
        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/bloquear")
    public String doBloquearUsuario (@RequestParam("id") Integer id, Model model){
        UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        AsignacionEntity asignacionPersonal = this.asignacionRepository.findByUsuarioIdEmpresa(usuario.getId());
        this.asignacionRepository.delete(asignacionPersonal);
        // if(asignacionPersonal==null) return "aviso";
       return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/paginaempresa")
    public String doPaginaPrincipalEmpresa(Model model, HttpSession sesion) {
        UsuarioEntity usuario = (UsuarioEntity) sesion.getAttribute("user");

        if (usuario != null && usuario.getSubrol().equalsIgnoreCase("")) {
            model.addAttribute("empresa", usuario);
            AsignacionEntity asignacionEmpresa = this.asignacionRepository.findByUsuarioIdEmpresa(usuario.getId());
            if(asignacionEmpresa == null){
                UsuarioEntity primerSocio = this.usuarioRepository.buscarSocioOriginal(usuario.getIdentificacion());
                AsignacionEntity asignacionPrimerSocio = this.asignacionRepository.findByUsuarioIdEmpresa(primerSocio.getId());
                if(asignacionPrimerSocio==null){
                    model.addAttribute("asignacion", null);
                    return "pgempresa";
                }
                asignacionEmpresa = new AsignacionEntity();
                asignacionEmpresa.setUsuarioId(usuario.getId());
                asignacionEmpresa.setCuentaBancariaId(asignacionPrimerSocio.getCuentaBancariaId());
                this.asignacionRepository.save(asignacionEmpresa);
            }
            model.addAttribute("asignacion", asignacionEmpresa);
            return "pgempresa";
        }

        else if(usuario!=null){
            AsignacionEntity asignacion = this.asignacionRepository.findByUsuarioIdEmpresa(usuario.getId());
            if(asignacion!=null){
                CuentaBancariaEntity cb = this.cuentaBancariaRepository.findById(asignacion.getCuentaBancariaId()).orElse(null);
                SolicitudActivacionEntity solicitudActivacion = this.solicitudActivacionRepository.buscarSolicitudActivacionPorUsuarioYCuenta(usuario,cb);

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
    @PostMapping("/filtrarNombre")
    public String doFiltrarN(@ModelAttribute("filtroNombre") usuarioFiltro uF, Model model, HttpSession sesion){
        subrolFiltro cF = new subrolFiltro();
        UsuarioEntity user = (UsuarioEntity) sesion.getAttribute("user");
        return  this.procesarFiltro2(uF,cF, model, user);
    }

    @PostMapping("/filtrarTipo")
    public String doFiltrarT (@ModelAttribute("filtroTipo") subrolFiltro cF, Model model, HttpSession sesion){
        usuarioFiltro uF = new usuarioFiltro();
        UsuarioEntity user = (UsuarioEntity) sesion.getAttribute("user");
        return this.procesarFiltro2(uF, cF, model, user);
    }
    @GetMapping("/personal")
    public String doPersonal(HttpSession sesion, Model model){
        UsuarioEntity usuario = (UsuarioEntity) sesion.getAttribute("user");
        usuarioFiltro uF = new usuarioFiltro();
        subrolFiltro cF = new subrolFiltro();
        return  this.procesarFiltro2(uF,cF, model,usuario);
    }
    public String procesarFiltro2(usuarioFiltro usrF, subrolFiltro cF, Model model, UsuarioEntity user){
        List<UsuarioEntity> lista;

        if((usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo() == null) || (usrF.getNombre() == null && usrF.getApellido() == null && cF.getTipo().equals(""))){
            lista = usuarioRepository.findEmpresaUsuariosSocioAutorizado(user.getIdentificacion());
        } else if (usrF == null || (usrF.getNombre() == null && usrF.getApellido() == null)) {
            lista = usuarioRepository.filtrarTipoSubrolEmpresa(cF.getTipo(),user.getIdentificacion());
        } else if (cF.getTipo() == null && usrF.getNombre().equals("")) {
            lista = usuarioRepository.filtrarApellidoEmpresa(usrF.getApellido(),user.getIdentificacion());
        } else if (cF.getTipo() == null && usrF.getApellido().equals("")) {
            lista = usuarioRepository.filtrarNombreEmpresa(usrF.getNombre(),user.getIdentificacion());
        } else {
            lista = usuarioRepository.filtrarNombreApellidoEmpresa(usrF.getNombre(), usrF.getApellido(),user.getIdentificacion());
        }
        UsuarioEntity empresa = this.usuarioRepository.buscarUsuarioEmpresaOriginal(user.getIdentificacion());
        AsignacionEntity asignacionEmpresa = this.asignacionRepository.findByUsuarioIdEmpresa(empresa.getId());

        List<AsignacionEntity> asignacionesEmpresa = this.asignacionRepository.asignacionesDeLaEmpresa(asignacionEmpresa.getCuentaBancariaId());

        model.addAttribute("filtroNombre", usrF);
        model.addAttribute("filtroTipo", cF);
        model.addAttribute("usuario",user);
        model.addAttribute("asignaciones",asignacionesEmpresa);
        model.addAttribute("personalempresa", lista);

        return "listarpersonal";
    }
    @GetMapping("/operaciones")
    public String doOperaciones(HttpSession sesion, Model model){
        UsuarioEntity usuario = (UsuarioEntity) sesion.getAttribute("user");

        opbFiltro filtro = new opbFiltro();
        return procesarFiltro(filtro, usuario.getId(), model);
    }

    public String procesarFiltro(opbFiltro filtro, Integer id, Model model){
        List<Integer> asignaciones = asignacionRepository.cuentasAsignadasPorUsuario(id);
        AsignacionEntity asignacion = this.asignacionRepository.findByUsuarioIdEmpresa(id);
        CuentaBancariaEntity cb = this.cuentaBancariaRepository.findById(asignacion.getCuentaBancariaId()).orElse(null);

        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.buscarOperacionesEmpresa(cb.getId());
        List<OperacionBancariaEntity> op = new ArrayList<>();
        if((filtro.getCantidad() == null && filtro.getFecha() == null) || filtro.getCantidad() == null && filtro.getFecha().isEmpty()) {
            filtro.setId(id);
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
        return "operaciones";
    }

    @PostMapping("/filtrarOperacion")
    public String recibirFiltro(@ModelAttribute("filtro") opbFiltro filtro,  Model model){
        return  this.procesarFiltro(filtro, filtro.getId(), model);
    }
    @GetMapping("/transferencia")
    public String doRealizarTransferencia(@RequestParam("id") Integer id, Model model, HttpSession sesion)      {
        // Creo la operación
        OperacionBancariaEntity operacion = new OperacionBancariaEntity();

        // Busco cuentaOrigen a través de la asignación que tiene personal con la cuenta de la empresa
        UsuarioEntity personal = (UsuarioEntity) sesion.getAttribute("user");
        AsignacionEntity asignacionPersonal = this.asignacionRepository.findByUsuarioIdEmpresa(personal.getId());
        CuentaBancariaEntity cuentaOrigen = this.cuentaBancariaRepository.findById(asignacionPersonal.getCuentaBancariaId()).orElse(null);

        // Añado al modelo
        model.addAttribute("personal", personal);
        model.addAttribute("operacion", operacion);
        model.addAttribute("cuentaorigen", cuentaOrigen);
        return "trans";
    }
    @PostMapping("/transhecha")
    public String doGuardarTransferencia(@ModelAttribute("op") OperacionBancariaEntity operacion, Model model, HttpSession sesion) {
        // Cuenta origen, Divisa origen, Cuenta destino, Divisa destino
        CuentaBancariaEntity cuentaOrigen = operacion.getCuentaBancariaByIdCuentaOrigen();
        DivisaEntity divisaOrigen = cuentaOrigen.getDivisaByDivisaId();

        CuentaBancariaEntity cuentaDestino = operacion.getCuentaBancariaByIdCuentaDestino();
        DivisaEntity divisaDestino = cuentaDestino.getDivisaByDivisaId();

        // Regulamos la operación con la fecha actual, y su cantidad para reflejar la cantidad que se le quita a la cuenta origen. OP destino.
        operacion.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        double cantidad = operacion.getCantidad();
        operacion.setCantidad(-cantidad);

        OperacionBancariaEntity operacionDestino = new OperacionBancariaEntity();
        operacionDestino.setFecha(operacion.getFecha());
        operacionDestino.setCantidad(cantidad * divisaDestino.getRatioDeCambio());
        operacionDestino.setCuentaBancariaByIdCuentaOrigen(cuentaDestino);
        operacionDestino.setCuentaBancariaByIdCuentaDestino(cuentaOrigen);

        UsuarioEntity usuario = (UsuarioEntity) sesion.getAttribute("user");
        operacion.setUsuarioByUsuario(usuario);
        operacionDestino.setUsuarioByUsuario(usuario);

        this.operacionBancariaRepository.save(operacion);
        this.operacionBancariaRepository.save(operacionDestino);

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);

        double cantidadOrigenAEuro = cantidad / divisaOrigen.getRatioDeCambio();
        double cantidadDestinoAEuro = cantidadOrigenAEuro * divisaDestino.getRatioDeCambio();

        double saldoDestino = cuentaDestino.getSaldo() + cantidadDestinoAEuro;

        cuentaDestino.setSaldo(saldoDestino);

        this.cuentaBancariaRepository.save(cuentaOrigen);
        this.cuentaBancariaRepository.save(cuentaDestino);
        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/cambiodivisa")
    public String doRealizarCambioDivisa(@RequestParam("id") Integer id, Model model){
        CuentaBancariaEntity cuentaEmpresa = this.cuentaBancariaRepository.findById(id).orElse(null);
        List<DivisaEntity> divisas = this.divisaRepository.findAll();

        model.addAttribute("divisas", divisas);
        model.addAttribute("cuentaempresa", cuentaEmpresa);

        return "divisacambio";
    }

    @PostMapping("/guardarcambiodivisa")
    public String doGuardarCambioDivisa(@ModelAttribute("cuentaempresa") CuentaBancariaEntity cuentaempresa){
        String moneda = cuentaempresa.getMoneda();
        DivisaEntity divisaAnterior = this.divisaRepository.buscarPorMoneda(moneda);
        DivisaEntity divisaActual = cuentaempresa.getDivisaByDivisaId();

        Double cantidadEuro = cuentaempresa.getSaldo()/divisaAnterior.getRatioDeCambio();

        cuentaempresa.setSaldo(cantidadEuro*divisaActual.getRatioDeCambio());
        cuentaempresa.setMoneda(cuentaempresa.getDivisaByDivisaId().getNombre());

        this.cuentaBancariaRepository.save(cuentaempresa);
        return "redirect:/empresa/paginaempresa";
    }

    @GetMapping("/solicitudactivacion")
    public String doRealizarSolicitudActivacion(@RequestParam("id") Integer id, HttpSession sesion){
        UsuarioEntity usuarioActual = (UsuarioEntity) sesion.getAttribute("user");
        CuentaBancariaEntity cuentaEmpresa = this.cuentaBancariaRepository.findById(id).orElse(null);

        // Busco entre una lista de gestores el gestor que necesito
        EmpleadoEntity gestor = this.buscarGestor();

        SolicitudActivacionEntity nuevaSolicitud = new SolicitudActivacionEntity();
        nuevaSolicitud.setUsuarioByUsuarioId(usuarioActual);
        nuevaSolicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
        nuevaSolicitud.setCuentaBancariaByCuentaBancariaId(cuentaEmpresa);
        nuevaSolicitud.setEmpleadoByEmpleadoIdGestor(gestor);

        this.solicitudActivacionRepository.save(nuevaSolicitud);
        return "redirect:/empresa/paginaempresa";
    }

    public EmpleadoEntity buscarGestor(){
        List<EmpleadoEntity> gestores = this.empleadoRepository.todosLosGestores();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(gestores.size());
        EmpleadoEntity gestor = gestores.get(indiceAleatorio);
        return gestor;
    }
}
