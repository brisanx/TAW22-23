package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.SolicitudActivacionDTO;
import org.taw.gestorbanco.dto.SolicitudAltaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SolActivacionService {
    @Autowired
    protected ActivacionRepository activacionRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;


    /*------------------------------GESTORIA-------------------------*/
    public List<SolicitudActivacionDTO> doListar(){
        List<SolicitudActivacionEntity> lista = this.activacionRepository.findAll();
        return this.conversion(lista);
    }

    public List<SolicitudActivacionDTO> conversion(List<SolicitudActivacionEntity> lista){
        ArrayList s = new ArrayList<SolicitudActivacionDTO>();
        lista.forEach((final SolicitudActivacionEntity sol) -> s.add(sol.toDto()));
        return s;
    }

    /*---------------------------USUARIO------------------------------*/
    public void registrarSolicitudActivacion(UsuarioDTO dto, CuentaBancariaDTO cb){
        SolicitudActivacionEntity solicitud;
        solicitud = new SolicitudActivacionEntity();

        solicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));

        UsuarioEntity usuario = this.usuarioRepository.findById(dto.getId()).orElse(null);
        solicitud.setUsuarioByUsuarioId(usuario);

        CuentaBancariaEntity cuenta = this.cuentaBancariaRepository.findById(cb.getId()).orElse(null);

        solicitud.setCuentaBancariaByCuentaBancariaId(cuenta);

        List<EmpleadoEntity> gestores = this.empleadoRepository.todosLosGestores();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(gestores.size());
        EmpleadoEntity gestor = gestores.get(indiceAleatorio);
        solicitud.setEmpleadoByEmpleadoIdGestor(gestor);

        this.activacionRepository.save(solicitud);
    }

    /*---------EMPRESA------------------------*/
    public SolicitudActivacionDTO buscarSolicitudActivacionPorUsuarioCuenta(UsuarioDTO u, CuentaBancariaDTO cb) {
        UsuarioEntity usuario = this.usuarioRepository.findById(u.getId()).orElse(null);
        CuentaBancariaEntity cuenta = this.cuentaBancariaRepository.findById(cb.getId()).orElse(null);
        SolicitudActivacionEntity solicitud = this.activacionRepository.buscarSolicitudActivacionPorUsuarioYCuenta(usuario, cuenta);

        if (solicitud != null) {
            return solicitud.toDto();
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
        this.activacionRepository.save(nuevaSolicitud);
    }
}
