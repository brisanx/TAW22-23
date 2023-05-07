package org.taw.gestorbanco.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.*;
import org.taw.gestorbanco.filtros.crudSospechoso;
import org.taw.gestorbanco.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaBancariaService {

    Byte uno = 1;
    Byte cero = 0;
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;

    @Autowired
    protected ActivacionRepository activacionRepository;
    @Autowired
    protected AsignacionRepository asignacionRepository;

    @Autowired
    protected DivisasRepository divisaRepository;

    /*--------------------------------GESTORIA-----------------------------------*/

    public List<CuentaBancariaDTO> tienenQueBloquearse(Timestamp fecha, Byte uno){
        List<CuentaBancariaEntity> lista = cuentaBancariaRepository.filtroFecha(fecha, uno);
        return this.conversion(lista);
    }

    public List<Timestamp> ultimaFechaOperacionBancaria(List<CuentaBancariaDTO> lista) {
        ArrayList fechas = new ArrayList<Timestamp>();
        lista.forEach((final CuentaBancariaDTO cu) -> fechas.add(cuentaBancariaRepository.verFechas(cu.getId())));
        return fechas;
    }

    public void agregarCuentaSospechosa(crudSospechoso id){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id.getId()).orElse(null);
        cb.setSospechosa(uno);
        cuentaBancariaRepository.save(cb);
    }

    public void eliminarCuentaSospechosa(crudSospechoso id){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id.getId()).orElse(null);
        cb.setSospechosa(cero);
        cuentaBancariaRepository.save(cb);
    }

    public List<CuentaBancariaDTO> buscarCuentasSospechosas(){
        List<CuentaBancariaEntity> cSospechosas = cuentaBancariaRepository.cuentasSospechosas(uno);
        return this.conversion(cSospechosas);
    }

    public List<CuentaBancariaDTO> comienzoNoSospechosos(){
        List<CuentaBancariaEntity> comienzo = cuentaBancariaRepository.findAll();
        return this.conversion(comienzo);
    }

    public List<CuentaBancariaDTO> cuentasNoSospechosas(){
        List<CuentaBancariaEntity> noSospechosos = cuentaBancariaRepository.noSospechosos(cero);
        return this.conversion(noSospechosos);
    }



    public void aceptarActivacion (Integer id, Integer solID){
        SolicitudActivacionEntity sol = activacionRepository.findById(solID).orElse(null);

        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id).orElse(null);
        cb.setActivo(uno);

        OperacionBancariaEntity op = new OperacionBancariaEntity();
        op.setCantidad(0.0);
        op.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        op.setCuentaBancariaByIdCuentaDestino(cb);
        op.setCuentaBancariaByIdCuentaOrigen(cb);
        op.setUsuarioByUsuario(sol.getUsuarioByUsuarioId());

        List<OperacionBancariaEntity> ops = (List<OperacionBancariaEntity>) cb.getOperacionBancariasById();
        ops.add(op);
        cb.setOperacionBancariasById(ops);

        cuentaBancariaRepository.save(cb);
        operacionBancariaRepository.save(op);
        activacionRepository.deleteById(solID);
    }

    public void denegarActivacion(Integer solId){
        activacionRepository.deleteById(solId);
    }

    public void desactivarCuenta(Integer id){
        CuentaBancariaEntity cb = cuentaBancariaRepository.findById(id).orElse(null);
        cb.setActivo(cero);
        cuentaBancariaRepository.save(cb);
    }

    /*------------------------------------------------------------USUARIO-------------------------------*/
    public List<CuentaBancariaDTO> obtenerCuentaBancaria(UsuarioDTO usuario){
        List<Integer> asignacion = this.asignacionRepository.findByUsuarioId(usuario.getId());
        List<CuentaBancariaEntity> lista = this.cuentaBancariaRepository.encontrarCuentaPorAsignacion(asignacion);

        return this.conversion(lista);
    }

    public List<CuentaBancariaDTO> conversion(List<CuentaBancariaEntity> lista){
        ArrayList cuentas = new ArrayList<CuentaBancariaDTO>();
        lista.forEach((final CuentaBancariaEntity cu) -> cuentas.add(cu.toDto()));
        return cuentas;
    }

    public CuentaBancariaDTO buscarCuenta(Integer cuentaBancariaId) {
        CuentaBancariaEntity cb = this.cuentaBancariaRepository.findById(cuentaBancariaId).orElse(null);
        return cb.toDto();
    }

    public void ajustarSaldos(OperacionBancariaDTO dto) {
        CuentaBancariaEntity cuentaOrigen = this.cuentaBancariaRepository.findById(dto.getCuentaBancariaByIdCuentaOrigen().getId()).orElse(null);
        DivisaEntity divisaOrigen = cuentaOrigen.getDivisaByDivisaId();

        CuentaBancariaEntity cuentaDestino = this.cuentaBancariaRepository.findById(dto.getCuentaBancariaByIdCuentaDestino().getId()).orElse(null);
        DivisaEntity divisaDestino = cuentaDestino.getDivisaByDivisaId();

        double cantidad = dto.getCantidad();
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);

        double cantidadOrigenAEuro = cantidad / divisaOrigen.getRatioDeCambio();
        double cantidadDestinoAEuro = cantidadOrigenAEuro * divisaDestino.getRatioDeCambio();
        double saldoDestino = cuentaDestino.getSaldo() + cantidadDestinoAEuro;

        cuentaDestino.setSaldo(saldoDestino);

        this.cuentaBancariaRepository.save(cuentaOrigen);
        this.cuentaBancariaRepository.save(cuentaDestino);
    }

    public void cambiarDivisa(CuentaBancariaDTO dto){
        CuentaBancariaEntity cuenta = this.cuentaBancariaRepository.findById(dto.getId()).orElse(null);
        DivisaEntity antigua = this.divisaRepository.buscarPorMoneda(dto.getMoneda());
        DivisaEntity nueva = this.divisaRepository.getById(dto.getDivisaByDivisaId().getId());

        cuenta.setSaldo((dto.getSaldo()/antigua.getRatioDeCambio())*nueva.getRatioDeCambio());

        cuenta.setMoneda(nueva.getNombre());
        cuenta.setDivisaByDivisaId(nueva);
        this.cuentaBancariaRepository.save(cuenta);
    }
}
