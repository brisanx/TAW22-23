package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

@Service
public class OperacionBancariaService {
    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public void guardarOperacionBancaria(OperacionBancariaDTO dto){
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
}
