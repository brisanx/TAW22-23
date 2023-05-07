package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;
import org.taw.gestorbanco.filtros.opbFiltro;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Torres y Miguel Moya
 */
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

        return this.listaEntidadesADTO(operaciones);
    }
    public List<OperacionBancariaDTO> listarTodasOperacionesClientes(UsuarioDTO usuario, opbFiltro filtro){
        List<OperacionBancariaEntity> lista;
        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.buscarOperacionesClientesUsr(usuario.getId());
        if(filtro!=null){
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
        }else{
            lista=operaciones;
        }
        List<OperacionBancariaDTO> dtoList = new ArrayList<>();
        for(OperacionBancariaEntity op:lista){
            OperacionBancariaDTO dto = new OperacionBancariaDTO();
            dto.setUsuario(op.getUsuarioByUsuario());
            dto.setCantidad(op.getCantidad());
            dto.setCuentaBancariaByIdCuentaDestino(op.getCuentaBancariaByIdCuentaDestino());
            dto.setFecha(op.getFecha());
            dto.setId(op.getId());
            dto.setCuentaBancariaByIdCuentaOrigen(op.getCuentaBancariaByIdCuentaOrigen());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public void guardarOperacionBancaria(OperacionBancariaDTO dto){
        OperacionBancariaEntity operacion;
        operacion = new OperacionBancariaEntity();

        operacion.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        operacion.setCantidad(-dto.getCantidad());
        operacion.setCuentaBancariaByIdCuentaDestino(dto.getCuentaBancariaByIdCuentaDestino());
        operacion.setCuentaBancariaByIdCuentaOrigen(dto.getCuentaBancariaByIdCuentaOrigen());
        operacion.setUsuarioByUsuario(dto.getUsuario());

        this.operacionBancariaRepository.save(operacion);
    }

    public void guardarOperacionBancariaDestino(OperacionBancariaDTO origen){
        OperacionBancariaEntity destino = new OperacionBancariaEntity();
        destino.setFecha(origen.getFecha());
        destino.setCantidad(origen.getCantidad() * origen.getCuentaBancariaByIdCuentaDestino().getDivisaByDivisaId().getRatioDeCambio());
        destino.setCuentaBancariaByIdCuentaDestino(origen.getCuentaBancariaByIdCuentaOrigen());
        destino.setCuentaBancariaByIdCuentaOrigen(origen.getCuentaBancariaByIdCuentaDestino());
        destino.setUsuarioByUsuario(origen.getUsuario());
        this.operacionBancariaRepository.save(destino);

    }

    public void guardarSacarDinero(OperacionBancariaDTO dto){
        OperacionBancariaEntity operacion;
        operacion = new OperacionBancariaEntity();

        operacion.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        operacion.setCantidad(-dto.getCantidad());
        operacion.setCuentaBancariaByIdCuentaDestino(dto.getCuentaBancariaByIdCuentaOrigen());
        operacion.setCuentaBancariaByIdCuentaOrigen(dto.getCuentaBancariaByIdCuentaOrigen());
        operacion.setUsuarioByUsuario(dto.getUsuario());

        this.operacionBancariaRepository.save(operacion);
    }



    private List<OperacionBancariaDTO> listaEntidadesADTO(List<OperacionBancariaEntity> lista) {
        ArrayList dtos = new ArrayList<UsuarioDTO>();
        lista.forEach(OperacionBancariaEntity -> dtos.add(OperacionBancariaEntity.toDTO()));
        return dtos;
    }
}
