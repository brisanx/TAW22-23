package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionBancariaService {
    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    public List<OperacionBancariaDTO> listarOperacionesClientes(UsuarioDTO usuario){
        AsignacionEntity asignacion =
                this.asignacionRepository.findByUsuarioId(usuario.getId());
        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.buscarOperacionesClientes(asignacion.getCuentaBancariaId());

        return this.listaOperacionesADTO(operaciones);
    }

    public void guardarOperacionBancaria(OperacionBancariaDTO dto){
        OperacionBancariaEntity operacion;
        operacion = new OperacionBancariaEntity();

        operacion.setFecha(dto.getFecha());
        operacion.setCantidad(-dto.getCantidad());
        operacion.setCuentaBancariaByIdCuentaDestino(dto.getCuentaBancariaByIdCuentaDestino());
        operacion.setCuentaBancariaByIdCuentaOrigen(dto.getCuentaBancariaByIdCuentaOrigen());

        this.operacionBancariaRepository.save(operacion);
    }

    private List<OperacionBancariaDTO> listaOperacionesADTO(List<OperacionBancariaEntity> operaciones) {
        ArrayList<OperacionBancariaDTO> dtos = new ArrayList<OperacionBancariaDTO>();
        operaciones.forEach((final OperacionBancariaEntity operacionBancaria) -> dtos.add(operacionBancaria.toDTO()));
        return dtos;
    }
}
