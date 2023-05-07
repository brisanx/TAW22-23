package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dao.OperacionBancariaRepository;
import org.taw.gestorbanco.dto.AsignacionDTO;
import org.taw.gestorbanco.dto.DivisaDTO;
import org.taw.gestorbanco.dto.OperacionBancariaDTO;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.OperacionBancariaEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivisaService {
    @Autowired
    DivisaRepository divisaRepository;
    @Autowired
    OperacionBancariaRepository operacionBancariaRepository;
    public List<DivisaDTO> buscarTodasLasDivisas() {
        List<DivisaEntity> divisas = this.divisaRepository.findAll();
       return this.listaEntidadesADTO(divisas);
    }
    protected List<DivisaDTO> listaEntidadesADTO(List<DivisaEntity> lista) {
        ArrayList dtos = new ArrayList<DivisaDTO>();
        lista.forEach(divisaEntity -> dtos.add(divisaEntity.toDTO()));
        return dtos;
    }

    public DivisaDTO buscarDivisaOrigen(OperacionBancariaDTO dto) {
        OperacionBancariaEntity op = this.operacionBancariaRepository.findById(dto.getId()).orElse(null);
        DivisaEntity divisa = op.getCuentaBancariaByIdCuentaOrigen().getDivisaByDivisaId();
        return divisa.toDTO();
    }

    public DivisaDTO buscarDivisaDestino(OperacionBancariaDTO dto) {
        OperacionBancariaEntity op = this.operacionBancariaRepository.findById(dto.getId()).orElse(null);
        DivisaEntity divisa = op.getCuentaBancariaByIdCuentaDestino().getDivisaByDivisaId();
        return divisa.toDTO();
    }
}
