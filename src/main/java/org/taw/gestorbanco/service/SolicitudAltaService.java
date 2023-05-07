package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dao.EmpleadoRepository;
import org.taw.gestorbanco.dao.SolicitudAltaRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.SolicitudAltaDTO;
import org.taw.gestorbanco.entity.SolicitudAltaEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo
 */
@Service
public class SolicitudAltaService {
    @Autowired
    SolicitudAltaRepository solicitudAltaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    DivisaRepository divisaRepository;

    public void guardarSolicitudAlta(SolicitudAltaDTO dto) {
        SolicitudAltaEntity solicitudEmpresa = new SolicitudAltaEntity();

        solicitudEmpresa.setIdSolicitud(dto.getIdSolicitud());
        solicitudEmpresa.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));

        UsuarioEntity personal = this.usuarioRepository.findByEmail(dto.getUsuarioByUsuarioId().getEmail());
        solicitudEmpresa.setUsuarioByUsuarioId(personal);
        solicitudEmpresa.setEmpleadoByIdGestor(empleadoRepository.findById(dto.getEmpleadoByIdGestor().getIdGestor()).orElse(null));
        solicitudEmpresa.setDivisaByDivisaId(divisaRepository.findById(dto.getDivisaByDivisaId().getId()).orElse(null));

        this.solicitudAltaRepository.save(solicitudEmpresa);

    }
}