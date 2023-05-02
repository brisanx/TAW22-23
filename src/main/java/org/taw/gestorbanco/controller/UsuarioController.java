package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dao.*;
import org.taw.gestorbanco.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UsuarioController {
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
    @GetMapping("/registro")
    public String doRegistroEmpresa(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        return "registroempresa";
    }
    @PostMapping("/guardar")
    public String doGuardarEmpresa(@ModelAttribute("usuario") UsuarioEntity usuario, Model model, HttpSession sesion) {
        this.usuarioRepository.save(usuario);

        CuentaBancariaEntity cb = new CuentaBancariaEntity();
        DivisaEntity divisa = this.divisaRepository.findById(1).orElse(null);

        cb.setSaldo(00.00);
        cb.setMoneda("Euro");
        cb.setSospechosa((byte) 0);
        cb.setActivo((byte) 1);
        cb.setDivisaByDivisaId(divisa);

        this.cuentaBancariaRepository.save(cb);

        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(usuario.getId());
        asignacion.setCuentaBancariaId(cb.getId());
        asignacionRepository.save(asignacion);

        UsuarioEntity socioautorizado = new UsuarioEntity();
        socioautorizado.setIdentificacion(usuario.getIdentificacion());
        model.addAttribute("usuariosocio", socioautorizado);
        return "personal";
    }
    @GetMapping("/guardarr")
    public String doGuardarEmpresaGet(HttpServletRequest request, Model model, HttpSession sesion) {
        UsuarioEntity socioautorizado = new UsuarioEntity();

        String idEmpresa = request.getParameter("identificacion");

        model.addAttribute("idEmpresa", idEmpresa);
        model.addAttribute("usuariosocio", socioautorizado);
        return "personal";
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
        return "redirect:/paginaempresa";
    }

    @PostMapping("/save")
    public String doSaveSocio(@ModelAttribute("usuariosocio") UsuarioEntity usuario, Model model, HttpSession sesion) {
        this.usuarioRepository.save(usuario);
        UsuarioEntity empresa = this.usuarioRepository.buscarUsuarioEmpresaOriginal(usuario.getIdentificacion());

        AsignacionEntity asi = this.asignacionRepository.findByUsuarioIdEmpresa(empresa.getId());

        AsignacionEntity nuevaAsignacion = new AsignacionEntity();
        nuevaAsignacion.setCuentaBancariaId(asi.getCuentaBancariaId());
        nuevaAsignacion.setUsuarioId(usuario.getId());
        this.asignacionRepository.save(nuevaAsignacion);

        return "redirect:/accesoUsuario";
    }
    @PostMapping("/otrosave")
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
        return "redirect:/paginaempresa";
    }


    @GetMapping("/bloquear")
    public String doBloquearUsuario (@RequestParam("id") Integer id, Model model){
        UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        if (usuario.getBloqueo()) usuario.setBloqueo(false);
        else usuario.setBloqueo(true);
        this.usuarioRepository.save(usuario);
       return "redirect:/paginaempresa";
    }
    @GetMapping("/paginaempresa")
    public String doPaginaPrincipalEmpresa(Model model, HttpSession sesion) {
        UsuarioEntity usuario = (UsuarioEntity) sesion.getAttribute("user");
        if (usuario != null && usuario.getSubrol().equalsIgnoreCase("")) {
            model.addAttribute("empresa", usuario);
            return "pgempresa";
        } else {
            UsuarioEntity empresa = this.usuarioRepository.buscarUsuarioEmpresaOriginal(usuario.getIdentificacion());
            List<UsuarioEntity> listaPersonalEmpresa = this.usuarioRepository.findEmpresaUsuariosSocioAutorizado(empresa.getIdentificacion());
            model.addAttribute("personalempresa", listaPersonalEmpresa);
            model.addAttribute("empresa", empresa);
            model.addAttribute("usuariosocio", usuario);
            return "pgsocioautorizado";
        }
    }
    @GetMapping("/transferencia")
    public String doRealizarTransferencia(@RequestParam("id") Integer id, Model model, HttpSession sesion)      {
        OperacionBancariaEntity op = new OperacionBancariaEntity();
        model.addAttribute("op", op);

        UsuarioEntity user = this.usuarioRepository.findById(id).orElse(null);
        UsuarioEntity empresa = (UsuarioEntity) this.usuarioRepository.buscarUsuarioEmpresaOriginal(user.getIdentificacion());
        AsignacionEntity asi = this.asignacionRepository.findByUsuarioIdEmpresa(empresa.getId());
        CuentaBancariaEntity cb = this.cuentaBancariaRepository.findById(asi.getCuentaBancariaId()).orElse(null);

        model.addAttribute("cuentaorigen", cb);
        return "trans";
    }
    @PostMapping("/transhecha")
    public String doGuardarTransferencia(@ModelAttribute("op") OperacionBancariaEntity op, Model model, HttpSession sesion) {
        op.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        this.operacionBancariaRepository.save(op);
        CuentaBancariaEntity cb = op.getCuentaBancariaByIdCuentaOrigen();
        cb.setSaldo(cb.getSaldo()-op.getCantidad());

        OperacionBancariaEntity op2 = new OperacionBancariaEntity();
        op2.setFecha(op.getFecha());
        op2.setCantidad(-op.getCantidad());
        op2.setCuentaBancariaByIdCuentaOrigen(op.getCuentaBancariaByIdCuentaDestino());
        op2.setCuentaBancariaByIdCuentaDestino(op.getCuentaBancariaByIdCuentaOrigen());

        CuentaBancariaEntity destino = op.getCuentaBancariaByIdCuentaDestino();
        destino.setSaldo(destino.getSaldo()+op.getCantidad());

        this.operacionBancariaRepository.save(op2);
        this.cuentaBancariaRepository.save(cb);
        this.cuentaBancariaRepository.save(destino);
        return "redirect:/paginaempresa";
    }

}
