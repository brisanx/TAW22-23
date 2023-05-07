package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;
import org.taw.gestorbanco.ui.opbFiltro;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo
 */
@Service
public class OperacionBancariaService {
    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public void guardarOperacionBancaria(OperacionBancariaDTO dto) {
        OperacionBancariaEntity operacion;
        operacion = new OperacionBancariaEntity();

        operacion.setFecha(dto.getFecha());
        operacion.setCantidad(-dto.getCantidad());
        operacion.setCuentaBancariaByIdCuentaDestino(this.cuentaBancariaRepository.findById(dto.getCuentaBancariaByIdCuentaDestino().getId()).orElse(null));
        operacion.setCuentaBancariaByIdCuentaOrigen(this.cuentaBancariaRepository.findById(dto.getCuentaBancariaByIdCuentaOrigen().getId()).orElse(null));
        operacion.setUsuarioByUsuario(this.usuarioRepository.findById(dto.getUsuario().getId()).orElse(null));
        this.operacionBancariaRepository.save(operacion);
    }

    public OperacionBancariaDTO setId(OperacionBancariaDTO operacionOrigen) {
        OperacionBancariaEntity cb = this.operacionBancariaRepository.ultimaOperacion();
        operacionOrigen.setId(cb.getId());
        return operacionOrigen;
    }

    public List<OperacionBancariaDTO> listarOperacionesEmpresa(Integer id) {
        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.buscarOperacionesEmpresa(id);
        return this.listaEntidadesADTO(operaciones);
    }

    protected List<OperacionBancariaDTO> listaEntidadesADTO(List<OperacionBancariaEntity> lista) {
        ArrayList dtos = new ArrayList<UsuarioDTO>();
        lista.forEach(OperacionBancariaEntity -> dtos.add(OperacionBancariaEntity.toDTO()));
        return dtos;
    }

    public List<OperacionBancariaDTO> listarOperacionesEmpresaPorAsignacion(List<Integer> asignaciones) {
        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.operacionesPorAsignacion(asignaciones);
        return this.listaEntidadesADTO(operaciones);
    }

    public List<OperacionBancariaDTO> listarOperaciones(Integer id, opbFiltro filtro) {
        List<OperacionBancariaEntity> lista;
        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.buscarOperacionesEmpresa(id);
        if (filtro.getCantidad() == null) {
            if (filtro.getMm()) {
                lista = operacionBancariaRepository.opsMenorFecha(operaciones, filtro.conversion());
            } else {
                lista = operacionBancariaRepository.opsMayorFecha(operaciones, filtro.conversion());
            }
        } else if (filtro.getFecha().isEmpty()) {
            if (filtro.getMmc()) {
                lista = operacionBancariaRepository.opsMenorCantidad(operaciones, filtro.getCantidad());
            } else {
                lista = operacionBancariaRepository.opsMayorCantidad(operaciones, filtro.getCantidad());
            }
        } else {
            if (filtro.getMm() && filtro.getMmc()) {
                lista = operacionBancariaRepository.opsMeMe(operaciones, filtro.getCantidad(), filtro.conversion());
            } else if (!filtro.getMm() && filtro.getMmc()) {
                lista = operacionBancariaRepository.opsMaMe(operaciones, filtro.getCantidad(), filtro.conversion());
            } else if (filtro.getMm() && !filtro.getMmc()) {
                lista = operacionBancariaRepository.opsMeMa(operaciones, filtro.getCantidad(), filtro.conversion());
            } else {
                lista = operacionBancariaRepository.opsMaMa(operaciones, filtro.getCantidad(), filtro.conversion());
            }
        }
        return this.listaEntidadesADTO(lista);
    }
}
