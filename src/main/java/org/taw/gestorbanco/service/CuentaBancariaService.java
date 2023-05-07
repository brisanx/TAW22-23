package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.DivisaEntity;

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

        cuenta.setSaldo((dto.getSaldo()/antigua.getRatioDeCambio())*dto.getDivisaByDivisaId().getRatioDeCambio());
        DivisaEntity nueva = this.divisaRepository.getById(dto.getDivisaByDivisaId().getId());

        cuenta.setMoneda(nueva.getNombre());
        this.cuentaBancariaRepository.save(cuenta);
    }
}
