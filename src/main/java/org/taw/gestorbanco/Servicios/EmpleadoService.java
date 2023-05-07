package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    protected EmpleadoRepository empleadoRepository;
    public EmpleadoDTO doAutenticarEmpleado(String user, String password){
        EmpleadoEntity empleado = this.empleadoRepository.autenticar(user, password);
        return (empleado == null ? null : empleado.toDto());
    }
}
