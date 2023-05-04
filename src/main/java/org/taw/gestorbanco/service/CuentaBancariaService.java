package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

/**
 * @author Jose Torres
 */
@Service
public class CuentaBancariaService {

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;

    public CuentaBancariaDTO obtenerCuentaBancaria(UsuarioDTO usuario){
        AsignacionEntity asignacion =
                this.asignacionRepository.findByUsuarioId(usuario.getId());

        CuentaBancariaEntity cuentaEntity =
                this.cuentaBancariaRepository.findById(asignacion.getCuentaBancariaId()).orElse(null);

        CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO();
        cuentaDTO.setId(cuentaEntity.getId());
        cuentaDTO.setSaldo(cuentaEntity.getSaldo());
        cuentaDTO.setMoneda(cuentaEntity.getMoneda());
        cuentaDTO.setSospechosa(cuentaEntity.getSospechosa());
        cuentaDTO.setActivo(cuentaEntity.getActivo());

        return cuentaDTO;
    }

    public void ajustarSaldos(OperacionBancariaDTO dto){
        CuentaBancariaEntity cuentaOrigen = dto.getCuentaBancariaByIdCuentaOrigen();
        CuentaBancariaEntity cuentaDestino = dto.getCuentaBancariaByIdCuentaDestino();

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()- dto.getCantidad());
        cuentaDestino.setSaldo(cuentaDestino.getSaldo()+dto.getCantidad());

        this.cuentaBancariaRepository.save(cuentaOrigen);
        this.cuentaBancariaRepository.save(cuentaDestino);
    }
}
