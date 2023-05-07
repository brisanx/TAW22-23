package org.taw.gestorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.ConversacionEntity;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.service.ConversacionService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ConversacionGestorController {

    @Autowired
    private ConversacionService conversacionService;

    @GetMapping("/home/gestor/conversacion")
    public String homeUserConversacion(Model model, HttpSession session){
        EmpleadoDTO empleadoDTO  = (EmpleadoDTO) session.getAttribute("user");;
        model.addAttribute("conversaciones", this.conversacionService.listarGestorConversaciones(empleadoDTO));
        List<EmpleadoEntity> gestores = conversacionService.todosLosAsistentes();
        model.addAttribute("gestores", gestores);
        model.addAttribute("usuarios", conversacionService.todosLosUsuarios());
        return "gestorChat";
    }

    @GetMapping("/home/gestor/conversacion/chat/{id}")
    public String chatConversacion(@PathVariable String id ,  Model model, HttpSession session){
        EmpleadoDTO empleadoDTO = (EmpleadoDTO) session.getAttribute("user");
        session.setAttribute("sender",empleadoDTO.getEmail());
        session.setAttribute("id",id);
        model.addAttribute("mensajes", this.conversacionService.listarMensajes(Integer.parseInt(id)));
        model.addAttribute("lectura", true);
        return "gstChat";
    }

    @PostMapping("/home/gestor/conversacion/chat/user")
    public String chatUserConversacion(@RequestParam String id ,  Model model, HttpSession session){
        EmpleadoDTO empleadoDTO = (EmpleadoDTO) session.getAttribute("user");
        session.setAttribute("sender",empleadoDTO.getEmail());
        session.setAttribute("id",id);
        model.addAttribute("mensajes", this.conversacionService.listarMensajesUser(Integer.parseInt(id)));
        model.addAttribute("lectura", false);
        return "gstChat";
    }

    @GetMapping("/home/gestor/conversacion/lectura/chat/{id}")
    public String chatLecturaConversacion(@PathVariable String id ,  Model model, HttpSession session){
        EmpleadoDTO empleadoDTO = (EmpleadoDTO) session.getAttribute("user");
        session.setAttribute("sender",empleadoDTO.getEmail());
        session.setAttribute("id",id);
        model.addAttribute("mensajes", this.conversacionService.listarMensajes(Integer.parseInt(id)));
        model.addAttribute("lectura", false);
        return "gstChat";
    }

    @PostMapping("/home/gestor/conversacion/chat/insertar")
    public String enviarMensaje(@RequestParam String texto, Model model, HttpSession session){
        String sender = session.getAttribute("sender").toString();
        String id= session.getAttribute("id").toString();
        this.conversacionService.crearMensaje(Integer.parseInt(id),sender,texto);
        model.addAttribute("mensajes", this.conversacionService.listarMensajes(Integer.parseInt(id)));
        model.addAttribute("lectura", true);
        return "chat";
    }

    @PostMapping("/home/gestor/conversacion/buscar")
    public String buscarConversacion(@RequestParam int empleadoId, @RequestParam int userId, @RequestParam int estado, @RequestParam String numeroMensajesInternal, @RequestParam String order, Model model, HttpSession session){
        EmpleadoDTO empleadoDTO = (EmpleadoDTO) session.getAttribute("user");
        int numeroMensajes =-1;
        try {
            numeroMensajes = Integer.parseInt(numeroMensajesInternal);
        } catch (NumberFormatException e) {

        }
        List<ConversacionEntity> entities = this.conversacionService.buscarQueryConversacionesEntity(empleadoId,userId,estado,null,null,numeroMensajes,order);
        model.addAttribute("conversaciones", entities);
        List<EmpleadoEntity> gestores = conversacionService.todosLosAsistentes();
        model.addAttribute("gestores", gestores);
        model.addAttribute("usuarios", conversacionService.todosLosUsuarios());

        return "gestorChat";
    }
}
