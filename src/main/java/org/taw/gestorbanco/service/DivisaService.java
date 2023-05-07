package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dto.DivisaDTO;
import org.taw.gestorbanco.entity.DivisaEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class DivisaService {
    @Autowired
    DivisaRepository divisaRepository;

    public List<DivisaDTO> buscarTodasLasDivisas() {
        List<DivisaEntity> divisas = this.divisaRepository.findAll();
        return this.listaEntidadesADTO(divisas);
    }

    public DivisaDTO divisaPorNombre(String nombre){
        DivisaEntity divEntity = this.divisaRepository.buscarDivisaPorNombre(nombre);
        return divEntity.toDTO();
    }
    protected List<DivisaDTO> listaEntidadesADTO(List<DivisaEntity> lista) {
        ArrayList dtos = new ArrayList<DivisaDTO>();
        lista.forEach(divisaEntity -> dtos.add(divisaEntity.toDTO()));
        return dtos;
    }

}
