package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.EmpleadoRepository;
import org.taw.gestorbanco.dao.SolicitudActivacionRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.SolicitudActivacionDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.SolicitudActivacionEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo
 */
@Service
public class SolicitudActivacionService {
    @Autowired
    SolicitudActivacionRepository solicitudActivacionRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;

    public SolicitudActivacionDTO buscarSolicitudActivacionPorUsuarioCuenta(UsuarioDTO u, CuentaBancariaDTO cb) {
        UsuarioEntity usuario = this.usuarioRepository.findById(u.getId()).orElse(null);
        CuentaBancariaEntity cuenta = this.cuentaBancariaRepository.findById(cb.getId()).orElse(null);
        SolicitudActivacionEntity solicitud = this.solicitudActivacionRepository.buscarSolicitudActivacionPorUsuarioYCuenta(usuario, cuenta);

        if (solicitud != null) {
            return solicitud.toDTO();
        } else {
            return null;
        }
    }

    public void guardarSolicitud(SolicitudActivacionDTO dto) {
        SolicitudActivacionEntity nuevaSolicitud = new SolicitudActivacionEntity();
        nuevaSolicitud.setUsuarioByUsuarioId(this.usuarioRepository.findById(dto.getUsuarioByUsuarioId().getId()).orElse(null));
        nuevaSolicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
        nuevaSolicitud.setCuentaBancariaByCuentaBancariaId(this.cuentaBancariaRepository.findById(dto.getCuentaBancariaByCuentaBancariaId().getId()).orElse(null));
        nuevaSolicitud.setEmpleadoByEmpleadoIdGestor(this.empleadoRepository.findById(dto.getEmpleadoByEmpleadoIdGestor().getIdGestor()).orElse(null));
        this.solicitudActivacionRepository.save(nuevaSolicitud);
    }
}
