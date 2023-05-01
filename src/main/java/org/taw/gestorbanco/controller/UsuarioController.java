package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.entity.UsuarioEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String doRegistroEmpresa(Model model) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.addAttribute("usuario", usuario);
        return "registroempresa";
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
    @GetMapping("/guardarr")
    public String doGuardarEmpresaGet(HttpServletRequest request, Model model, HttpSession sesion) {
        UsuarioEntity socioautorizado = new UsuarioEntity();

        String idEmpresa = request.getParameter("identificacion");

        model.addAttribute("idEmpresa", idEmpresa);
        model.addAttribute("usuariosocio", socioautorizado);
        return "personal";
    }
    @PostMapping("/save")
    public String doSaveSocio(@ModelAttribute("usuariosocio") UsuarioEntity usuario, Model model, HttpSession sesion) {
        this.usuarioRepository.save(usuario);
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
    @PostMapping("/guardar")
    public String doGuardarEmpresa(@ModelAttribute("usuario") UsuarioEntity usuario, Model model, HttpSession sesion) {
        this.usuarioRepository.save(usuario);
        UsuarioEntity socioautorizado = new UsuarioEntity();
        socioautorizado.setIdentificacion(usuario.getIdentificacion());
        model.addAttribute("usuariosocio", socioautorizado);
        return "personal";
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
}
