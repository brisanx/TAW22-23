package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.SolicitudAltaDTO;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.repositories.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolAltaService {

    Byte cero = 0;
    Byte uno = 1;
    @Autowired
    protected AltaRepository altaRepository;

    @Autowired
    protected DivisasRepository divisasRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected  EmpleadoRepository empleadoRepository;

    @Autowired
    protected DivisasRepository divisaRepository;

    public List<SolicitudAltaDTO> doListar(){
        List<SolicitudAltaEntity> lista = this.altaRepository.findAll();
        return this.conversion(lista);
    }

    public List<SolicitudAltaDTO> conversion(List<SolicitudAltaEntity> lista){
        ArrayList s = new ArrayList<SolicitudAltaDTO>();
        lista.forEach((final SolicitudAltaEntity sol) -> s.add(sol.toDto()));
        return s;
    }

    public void eliminiarSolicitud(Integer id){
        altaRepository.deleteById(id);
    }

    public void aceptarSolicitud(Integer id, Integer solId, Integer divisa){
        DivisaEntity divisaSol = divisasRepository.findById(divisa).orElse(null);

        CuentaBancariaEntity cuentaNueva = new CuentaBancariaEntity();

        cuentaNueva.setSaldo(0.0);
        cuentaNueva.setMoneda(divisaSol.getNombre());
        cuentaNueva.setSospechosa(cero);
        cuentaNueva.setActivo(uno);
        cuentaNueva.setDivisaByDivisaId(divisaSol);

        cuentaBancariaRepository.save(cuentaNueva);
        CuentaBancariaEntity laNueva = cuentaBancariaRepository.ultimaCuenta();

        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(id);
        asignacion.setCuentaBancariaId(laNueva.getId());
        System.out.println(asignacion.getUsuarioId().getClass() + "---" + asignacion.getCuentaBancariaId().getClass());
        asignacionRepository.save(asignacion);
    }

    /*--------------------------------EMPRESA--------------------------------*/

    public void guardarSolicitudAlta(SolicitudAltaDTO dto) {
        SolicitudAltaEntity solicitudEmpresa = new SolicitudAltaEntity();

        //solicitudEmpresa.setIdSolicitud(dto.getIdSolicitud());
        solicitudEmpresa.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()));

        UsuarioEntity personal = this.usuarioRepository.findByEmail(dto.getUsuarioByUsuarioId().getEmail());
        solicitudEmpresa.setUsuarioByUsuarioId(personal);
        solicitudEmpresa.setEmpleadoByIdGestor(empleadoRepository.findById(dto.getEmpleadoByIdGestor().getIdGestor()).orElse(null));
        solicitudEmpresa.setDivisaByDivisaId(divisaRepository.findById(dto.getDivisaByDivisaId().getId()).orElse(null));

        this.altaRepository.save(solicitudEmpresa);

    }


}
