package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.repositories.EmpleadoRepository;

import java.util.List;
import java.util.Random;

@Service
public class EmpleadoService {

    @Autowired
    protected EmpleadoRepository empleadoRepository;
    public EmpleadoDTO doAutenticarEmpleado(String user, String password){
        EmpleadoEntity empleado = this.empleadoRepository.autenticar(user, password);
        return (empleado == null ? null : empleado.toDto());
    }

    /*---------------------------------------USUARIO---------------------------------*/
    public EmpleadoDTO buscarGestor(){
        List<EmpleadoEntity> gestores = this.empleadoRepository.todosLosGestores();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(gestores.size());
        EmpleadoEntity gestor = gestores.get(indiceAleatorio);

        return gestor.toDto();
    }
}
