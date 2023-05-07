package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.repositories.AsignacionRepository;

import java.util.List;

@Service
public class AsignacionService {
    @Autowired
    protected AsignacionRepository asignacionRepository;

    public List<Integer> listaAsignacionesPorId(Integer userId){
        return asignacionRepository.cuentasAsignadasPorUsuario(userId);
    }
}
