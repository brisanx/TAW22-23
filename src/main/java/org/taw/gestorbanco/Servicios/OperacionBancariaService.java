package org.taw.gestorbanco.Servicios;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;
import org.taw.gestorbanco.filtros.opbFiltro;
import org.taw.gestorbanco.repositories.AsignacionRepository;
import org.taw.gestorbanco.repositories.CuentaBancariaRepository;
import org.taw.gestorbanco.repositories.OperacionBancariaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperacionBancariaService {
    Byte uno = 1;
    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    /*---------------------------------GESTORIA-----------------------------------*/

    public List<OperacionBancariaDTO> operacionesPorAignaciones(List<Integer> asignaciones){
        List<OperacionBancariaEntity> operaciones = operacionBancariaRepository.operacionesPorAsignacion(asignaciones);

        return this.conversion(operaciones);
    }

    public List<OperacionBancariaDTO> operacionesPorFiltro(opbFiltro filtro, List<Integer> asignaciones){
        List<OperacionBancariaEntity> operaciones = operacionBancariaRepository.operacionesPorAsignacion(asignaciones);
        List<OperacionBancariaEntity> op = new ArrayList<>();
        if (filtro.getCantidad() == null) {
            System.out.println("1");
            if(filtro.getMm()) {
                op = operacionBancariaRepository.opsMenorFecha(operaciones, filtro.conversion());
            } else{
                System.out.println("1-2    " + filtro.conversion());
                op = operacionBancariaRepository.opsMayorFecha(operaciones, filtro.conversion());
            }
        } else if (filtro.getFecha().isEmpty()) {
            System.out.println("2");
            if(filtro.getMmc()){
                op = operacionBancariaRepository.opsMenorCantidad(operaciones, filtro.getCantidad());
            } else {
                op = operacionBancariaRepository.opsMayorCantidad(operaciones, filtro.getCantidad());
            }
        } else {
            System.out.println("3");
            if(filtro.getMm() && filtro.getMmc()) {
                op = operacionBancariaRepository.opsMeMe(operaciones, filtro.getCantidad(), filtro.conversion());
            } else if (!filtro.getMm() && filtro.getMmc()) {
                op = operacionBancariaRepository.opsMaMe(operaciones, filtro.getCantidad(), filtro.conversion());
            } else if (filtro.getMm() && !filtro.getMmc()) {
                op = operacionBancariaRepository.opsMeMa(operaciones, filtro.getCantidad(), filtro.conversion());
            } else {
                op = operacionBancariaRepository.opsMaMa(operaciones, filtro.getCantidad(), filtro.conversion());
            }
        }
        ArrayList ops = new ArrayList();
        op.forEach((final OperacionBancariaEntity ope) -> ops.add(ope.toDto()));
        return ops;
    }

    public List<OperacionBancariaDTO> operacionesSospechosas() {
        List<CuentaBancariaEntity> cSospechosas = cuentaBancariaRepository.cuentasSospechosas(uno);
        List<CuentaBancariaEntity> cuentasCuidado = cuentaBancariaRepository.encontrarTransferenciasSospechosas(cSospechosas, uno);

        List<OperacionBancariaEntity> operaciones = operacionBancariaRepository.ultimasOperacionesSospechosas(cSospechosas, cuentasCuidado);

        return this.conversion(operaciones);
    }

    /*---------------------------------CLIENTE--------------------------------------------*/

    public List<OperacionBancariaDTO> listarOperacionesClientes(Integer usuarioId){
        List<Integer> asignacion = this.asignacionRepository.findByUsuarioId(usuarioId);
        List<OperacionBancariaEntity> operaciones = this.operacionBancariaRepository.operacionesPorAsignacion(asignacion);

        return this.conversion(operaciones);
    }

    /*public void guardarOperacionBancaria(OperacionBancariaDTO dto){
        OperacionBancariaEntity operacion;
        operacion = new OperacionBancariaEntity();

        operacion.setFecha(dto.getFecha());
        operacion.setCantidad(-dto.getCantidad());
        operacion.setCuentaBancariaByIdCuentaDestino(dto.getCuentaBancariaByIdCuentaDestino());
        operacion.setCuentaBancariaByIdCuentaOrigen(dto.getCuentaBancariaByIdCuentaOrigen());

        this.operacionBancariaRepository.save(operacion);
    }*/

    public List<OperacionBancariaDTO> conversion(List<OperacionBancariaEntity> operaciones) {
        ArrayList ops = new ArrayList<OperacionBancariaDTO>();
        operaciones.forEach((final OperacionBancariaEntity op) -> ops.add(op.toDto()));
        return ops;
    }
}
