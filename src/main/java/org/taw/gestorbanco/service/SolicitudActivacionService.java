package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.*;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.SolicitudActivacionDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jose Torres
 */
@Service
public class SolicitudActivacionService {
    @Autowired
    protected SolicitudActivacionRepository solicitudActivacionRepository;
    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    public void registrarSolicitudActivacion(UsuarioDTO dto, CuentaBancariaDTO cb){
        SolicitudActivacionEntity solicitud;
        solicitud = new SolicitudActivacionEntity();

        solicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));

        UsuarioEntity usuario = this.usuarioRepository.findById(dto.getId()).orElse(null);
        solicitud.setUsuarioByUsuarioId(usuario);

        AsignacionEntity asignacion = this.asignacionRepository.findByUsuarioId(usuario.getId());

        CuentaBancariaEntity cuenta = this.cuentaBancariaRepository.findById(asignacion.getCuentaBancariaId()).orElse(null);

        solicitud.setCuentaBancariaByCuentaBancariaId(cuenta);

        List<EmpleadoEntity> gestores = this.empleadoRepository.todosLosGestores();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(gestores.size());
        EmpleadoEntity gestor = gestores.get(indiceAleatorio);
        solicitud.setEmpleadoByEmpleadoIdGestor(gestor);

        this.solicitudActivacionRepository.save(solicitud);
    }

    public void guardarSolicitud(SolicitudActivacionDTO dto) {
        SolicitudActivacionEntity nuevaSolicitud = new SolicitudActivacionEntity();
        nuevaSolicitud.setUsuarioByUsuarioId(this.usuarioRepository.findById(dto.getUsuarioByUsuarioId().getId()).orElse(null));
        nuevaSolicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));
        nuevaSolicitud.setCuentaBancariaByCuentaBancariaId(this.cuentaBancariaRepository.findById(dto.getCuentaBancariaByCuentaBancariaId().getId()).orElse(null));
        nuevaSolicitud.setEmpleadoByEmpleadoIdGestor(this.empleadoRepository.findById(dto.getEmpleadoByEmpleadoIdGestor().getIdGestor()).orElse(null));
        this.solicitudActivacionRepository.save(nuevaSolicitud);
    }

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

    }
