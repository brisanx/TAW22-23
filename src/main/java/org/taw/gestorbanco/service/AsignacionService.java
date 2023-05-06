package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dto.AsignacionDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;

@Service
public class AsignacionService {
    @Autowired
    protected AsignacionRepository asignacionRepository;

    public AsignacionDTO buscarAsignacion(Integer id) {
        AsignacionEntity asignacionEmpresa = this.asignacionRepository.findByUsuarioIdEmpresa(id);
        return asignacionEmpresa.toDTO();
    }

    public void guardarAsignacion(AsignacionDTO dto) {
        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(dto.getUsuarioId());
        asignacion.setCuentaBancariaId(dto.getCuentaBancariaId());
        this.asignacionRepository.save(asignacion);
    }

    public void eliminarAsignacion(Integer id) {
        AsignacionEntity asignacion = this.asignacionRepository.findByUsuarioIdEmpresa(id);
        this.asignacionRepository.delete(asignacion);
    }
}
