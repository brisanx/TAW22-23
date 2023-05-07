package org.taw.gestorbanco.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.service.ConversacionService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ConversacionController {

    @Autowired
    private ConversacionService conversacionService;


    @GetMapping("/home/user/conversacion")
    public String homeUserConversacion(Model model, HttpSession session){
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");;
        model.addAttribute("conversaciones", this.conversacionService.listarConversaciones(usuarioDTO));
        List<EmpleadoEntity> gestores = conversacionService.todosLosAsistentes();
        model.addAttribute("gestores", gestores);
        return "usuarioChat";
    }

    @GetMapping("/home/user/conversacion/chat/{id}")
    public String chatConversacion(@PathVariable String id ,  Model model, HttpSession session){
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");
        session.setAttribute("sender",usuarioDTO.getEmail());
        session.setAttribute("id",id);
        model.addAttribute("mensajes", this.conversacionService.listarMensajes(Integer.parseInt(id)));
        return "chat";
    }

    @GetMapping("/home/user/conversacion/chat/cerrar/{id}")
    public String chatCerrarConversacion(@PathVariable String id ,  Model model, HttpSession session){
        this.conversacionService.cerrarConversacion(Integer.parseInt(id));
        return "redirect:/home/user/conversacion";

    }

    @PostMapping("/home/user/conversacion/insertar")
    public String chatInsertarConversacion(@RequestParam int gestor, Model model, HttpSession session){
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("user");;
        this.conversacionService.crearConversacion(usuarioDTO.getId(), gestor);
        return "redirect:/home/user/conversacion";
    }

    @PostMapping("/home/user/conversacion/chat/insertar")
    public String enviarMensaje(@RequestParam String texto, Model model, HttpSession session){
        String sender = session.getAttribute("sender").toString();
        String id= session.getAttribute("id").toString();
        this.conversacionService.crearMensaje(Integer.parseInt(id),sender,texto);
        model.addAttribute("mensajes", this.conversacionService.listarMensajes(Integer.parseInt(id)));
        return "chat";
    }
}
