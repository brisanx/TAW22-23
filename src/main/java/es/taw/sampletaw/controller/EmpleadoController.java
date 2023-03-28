package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.EmpleadoRepository;
import es.taw.sampletaw.entity.EmpleadoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmpleadoController {
    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @GetMapping("/")
    public String doListar(Model model){
        List<EmpleadoEntity> lista = this.empleadoRepository.findAll();
        model.addAttribute("empleado", lista);
        return "empleado";
    }
}
