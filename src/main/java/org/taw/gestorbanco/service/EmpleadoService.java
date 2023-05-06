package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.EmpleadoRepository;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.entity.EmpleadoEntity;

import java.util.List;
import java.util.Random;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    public EmpleadoDTO buscarGestor(){
        List<EmpleadoEntity> gestores = this.empleadoRepository.todosLosGestores();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(gestores.size());
        EmpleadoEntity gestor = gestores.get(indiceAleatorio);

        return gestor.toDTO();
    }


}
