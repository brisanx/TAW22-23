package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dto.AsignacionDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;

/**
 * @author Jose Torres
 */
@Service
public class AsignacionService {
    @Autowired
    protected AsignacionRepository asignacionRepository;

    public AsignacionDTO doAsignarCuentaACliente(Integer userId, Integer cuentaId){
        AsignacionEntity asignacion;
        asignacion = new AsignacionEntity();

        asignacion.setUsuarioId(userId);
    }
}
