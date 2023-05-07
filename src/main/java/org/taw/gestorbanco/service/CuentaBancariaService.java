package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.AsignacionDTO;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.DivisaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alba Sánchez Ibáñez, Fernando Calvo Díaz, José Torres Postigo, Miguel Moya Castillo
 */
@Service
public class CuentaBancariaService {
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    @Autowired
    protected OperacionBancariaRepository operacionBancariaRepository;
    @Autowired
    protected DivisaRepository divisaRepository;


    public CuentaBancariaDTO buscarCuenta(Integer cuentaBancariaId) {
        CuentaBancariaEntity cb = this.cuentaBancariaRepository.findById(cuentaBancariaId).orElse(null);
        return cb.toDTO();
    }

    protected List<AsignacionDTO> listaEntidadesADTO(List<AsignacionEntity> lista) {
        ArrayList dtos = new ArrayList<AsignacionDTO>();
        lista.forEach(CuentaBancariaEntity -> dtos.add(CuentaBancariaEntity.toDTO()));
        return dtos;
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