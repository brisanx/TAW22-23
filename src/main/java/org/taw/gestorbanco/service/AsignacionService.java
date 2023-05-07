package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dto.AsignacionDTO;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo
 */
@Service
public class AsignacionService {
    @Autowired
    protected AsignacionRepository asignacionRepository;
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    public List<AsignacionDTO> buscarAsignacion(Integer id) {
        List<AsignacionEntity> asignacionEmpresa = this.asignacionRepository.findByUsuarioId(id);
        if(asignacionEmpresa!=null){
            return this.listaEntidadesADTO(asignacionEmpresa);
        }
        return null;
    }

    public void guardarAsignacion(AsignacionDTO dto) {
        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(dto.getUsuarioId());
        asignacion.setCuentaBancariaId(dto.getCuentaBancariaId());
        this.asignacionRepository.save(asignacion);
    }

    public List<AsignacionDTO> buscarAsignacionesDeLaEmpresa(Integer cuentaBancariaId) {
        List<AsignacionEntity> asignaciones = this.asignacionRepository.asignacionesDeLaEmpresa(cuentaBancariaId);
        return this.listaEntidadesADTO(asignaciones);
    }
    protected List<AsignacionDTO> listaEntidadesADTO(List<AsignacionEntity> lista) {
        ArrayList dtos = new ArrayList<AsignacionDTO>();
        lista.forEach(AsignacionEntity -> dtos.add(AsignacionEntity.toDTO()));
        return dtos;
    }

    public List<Integer> todaslascuentasAsignadasPorUsuario(Integer id) {
        List<Integer> asignaciones;
        asignaciones = this.asignacionRepository.cuentasAsignadasPorUsuario(id);
        return asignaciones;
    }
    public List<CuentaBancariaDTO> cuentasAsignadasAParticular(Integer id) {
        List<AsignacionEntity> asignaciones = this.asignacionRepository.asignaciones(id);

        List<CuentaBancariaEntity> cuentas = new ArrayList<>();
        for(AsignacionEntity asig : asignaciones){
            cuentas.add(this.cuentaBancariaRepository.findById(asig.getCuentaBancariaId()).orElse(null));
        }

        ArrayList dtos = new ArrayList<CuentaBancariaDTO>();
        cuentas.forEach(CuentaEntity -> dtos.add(CuentaEntity.toDTO()));
        return dtos;
    }

    public List<Integer> listaAsignacionesPorId(Integer userId){
        return asignacionRepository.cuentasAsignadasPorUsuario(userId);
    }
}