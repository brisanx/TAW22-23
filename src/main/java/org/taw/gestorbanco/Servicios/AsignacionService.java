package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.AsignacionDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.repositories.AsignacionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignacionService {
    @Autowired
    protected AsignacionRepository asignacionRepository;

    public List<Integer> listaAsignacionesPorId(Integer userId){
        return asignacionRepository.cuentasAsignadasPorUsuario(userId);
    }

    /*---------------EMPRESA--------------*/

    public AsignacionDTO buscarAsignacion(Integer id) {
        AsignacionEntity asignacionEmpresa = this.asignacionRepository.findByUsuarioIdEmpresa(id);
        if(asignacionEmpresa!=null){
            return asignacionEmpresa.toDto();
        }
        return null;
    }
    public void guardarAsignacion(AsignacionDTO dto) {
        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(dto.getUserId());
        asignacion.setCuentaBancariaId(dto.getCuentaId());
        this.asignacionRepository.save(asignacion);
    }

    public void eliminarAsignacion(Integer id) {
        AsignacionEntity asignacion = this.asignacionRepository.findByUsuarioIdEmpresa(id);
        this.asignacionRepository.delete(asignacion);
    }

    public List<AsignacionDTO> buscarAsignacionesDeLaEmpresa(Integer cuentaBancariaId) {
        List<AsignacionEntity> asignaciones = this.asignacionRepository.asignacionesDeLaEmpresa(cuentaBancariaId);
        return this.listaEntidadesADTO(asignaciones);
    }
    protected List<AsignacionDTO> listaEntidadesADTO(List<AsignacionEntity> lista) {
        ArrayList dtos = new ArrayList<AsignacionDTO>();
        lista.forEach(AsignacionEntity -> dtos.add(AsignacionEntity.toDto()));
        return dtos;
    }

    public List<Integer> todaslascuentasAsignadasPorUsuario(Integer id) {
        List<Integer> asignaciones;
        asignaciones = this.asignacionRepository.cuentasAsignadasPorUsuario(id);
        return asignaciones;
    }
}
