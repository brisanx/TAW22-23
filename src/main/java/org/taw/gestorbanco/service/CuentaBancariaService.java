package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.CuentaBancariaDTO;
import org.taw.gestorbanco.dto.DivisaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Torres y Miguel Moya
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

    public CuentaBancariaDTO obtenerCuentaBancaria(UsuarioDTO usuario){
        AsignacionEntity asignacion =
                this.asignacionRepository.findByUsuarioId(usuario.getId());

        CuentaBancariaEntity cuentaEntity =
                this.cuentaBancariaRepository.findById(asignacion.getCuentaBancariaId()).orElse(null);

        CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO();
        cuentaDTO.setId(cuentaEntity.getId());
        cuentaDTO.setSaldo(cuentaEntity.getSaldo());
        cuentaDTO.setMoneda(cuentaEntity.getMoneda());
        cuentaDTO.setSospechosa(cuentaEntity.getSospechosa());
        cuentaDTO.setActivo(cuentaEntity.getActivo());

        return cuentaDTO;
    }

    public List<CuentaBancariaDTO> obtenerCuentasBancarias(UsuarioDTO usuario){
        List<AsignacionEntity> asignaciones =
                this.asignacionRepository.findAllByUsuarioId(usuario.getId());

        List<CuentaBancariaDTO> cuentas = new ArrayList<>();
        for(AsignacionEntity a: asignaciones){
            CuentaBancariaEntity cuentaEntity =
                    this.cuentaBancariaRepository.findById(a.getCuentaBancariaId()).orElse(null);

            CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO();
            cuentaDTO.setId(cuentaEntity.getId());
            cuentaDTO.setSaldo(cuentaEntity.getSaldo());
            cuentaDTO.setMoneda(cuentaEntity.getMoneda());
            cuentaDTO.setSospechosa(cuentaEntity.getSospechosa());
            cuentaDTO.setActivo(cuentaEntity.getActivo());
            cuentas.add(cuentaDTO);
        }
        return cuentas;
    }

    public CuentaBancariaDTO cuentaPorId(Integer id){
        CuentaBancariaEntity cuentaEntity = this.cuentaBancariaRepository.findById(id).orElse(null);
        CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO();
        cuentaDTO.setId(cuentaEntity.getId());
        cuentaDTO.setSaldo(cuentaEntity.getSaldo());
        cuentaDTO.setMoneda(cuentaEntity.getMoneda());
        cuentaDTO.setSospechosa(cuentaEntity.getSospechosa());
        cuentaDTO.setActivo(cuentaEntity.getActivo());
        return cuentaDTO;
    }

    public void ajustarSaldos(OperacionBancariaDTO dto){
        CuentaBancariaEntity cuentaOrigen = dto.getCuentaBancariaByIdCuentaOrigen();
        DivisaEntity divisaOrigen = cuentaOrigen.getDivisaByDivisaId();

        CuentaBancariaEntity cuentaDestino = dto.getCuentaBancariaByIdCuentaDestino();
        DivisaEntity divisaDestino = cuentaDestino.getDivisaByDivisaId();

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()- dto.getCantidad());

        Double cantidadConDivisas = (dto.getCantidad()*divisaDestino.getRatioDeCambio())/divisaOrigen.getRatioDeCambio();
        //Double cantidadOrigenADivisa = dto.getCantidad() * divisaOrigen.getRatioDeCambio();
        //Double saldoDestinoADivisa = cuentaDestino.getSaldo() * divisaDestino.getRatioDeCambio();

        //Double cantidadTotal = cantidadOrigenADivisa + saldoDestinoADivisa;
        cuentaDestino.setSaldo(cuentaDestino.getSaldo()+cantidadConDivisas);

        this.cuentaBancariaRepository.save(cuentaOrigen);
        this.cuentaBancariaRepository.save(cuentaDestino);
    }

    public void restarDineroSacado(OperacionBancariaDTO dto){
        CuentaBancariaEntity cuenta = dto.getCuentaBancariaByIdCuentaOrigen();
        cuenta.setSaldo(cuenta.getSaldo()- dto.getCantidad());
        this.cuentaBancariaRepository.save(cuenta);
    }

    public void cambiarDivisa(CuentaBancariaDTO dto){
        CuentaBancariaEntity cuenta = this.cuentaBancariaRepository.findById(dto.getId()).orElse(null);
        DivisaEntity antigua = this.divisaRepository.buscarDivisaPorNombre(dto.getMoneda());
        cuenta.setSaldo((dto.getSaldo()/antigua.getRatioDeCambio())*dto.getDivisaByDivisaId().getRatioDeCambio());
        DivisaEntity nueva = this.divisaRepository.getById(dto.getDivisaByDivisaId().getId());
        cuenta.setMoneda(nueva.getNombre());
        this.cuentaBancariaRepository.save(cuenta);
    }

    public List<CuentaBancariaDTO> cuentasActivasTodas(){
        List<CuentaBancariaEntity>cEntity = this.cuentaBancariaRepository.cuentasActivas();
        List<CuentaBancariaDTO> dtoList = new ArrayList<>();
        for(CuentaBancariaEntity c:cEntity){
            CuentaBancariaDTO dto = c.toDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }
}
