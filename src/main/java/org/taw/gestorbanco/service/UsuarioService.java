package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.AsignacionRepository;
import org.taw.gestorbanco.dao.CuentaBancariaRepository;
import org.taw.gestorbanco.dao.DivisaRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.AsignacionEntity;
import org.taw.gestorbanco.entity.CuentaBancariaEntity;
import org.taw.gestorbanco.entity.DivisaEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

/**
 * @author Jose Torres
 */
@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected DivisaRepository divisaRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected AsignacionRepository asignacionRepository;

    public UsuarioDTO doAutenticarUsuario(String user, String password){
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user, password);
        return (usuario == null ? null : usuario.toDTO());
    }

    public void guardarNuevoUsuario(UsuarioDTO dto){
        UsuarioEntity usuario;
        usuario = new UsuarioEntity();

        usuario.setId(dto.getId());
        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena());
        usuario.setRol(dto.getRol());
        usuario.setSubrol(dto.getSubrol());
        usuario.setDireccion(dto.getDireccion());
        usuario.setTelefono(dto.getTelefono());
        usuario.setAsignacionsById(dto.getAsignacionsById());
        usuario.setConversacionsById(dto.getConversacionsById());
        usuario.setSolicitudActivacionsById(dto.getSolicitudActivacionsById());
        usuario.setSolicitudAltasById(dto.getSolicitudAltasById());

        this.usuarioRepository.save(usuario);

        CuentaBancariaEntity cuenta;
        cuenta = new CuentaBancariaEntity();
        DivisaEntity divisa = this.divisaRepository.findById(1).orElse(null);

        cuenta.setSaldo(0.0);
        cuenta.setMoneda(divisa.getNombre());
        cuenta.setSospechosa((byte) 0);
        cuenta.setActivo((byte) 1);
        cuenta.setDivisaByDivisaId(divisa);

        this.cuentaBancariaRepository.save(cuenta);

        AsignacionEntity asignacion = new AsignacionEntity();
        asignacion.setUsuarioId(usuario.getId());
        asignacion.setCuentaBancariaId(cuenta.getId());
        this.asignacionRepository.save(asignacion);
    }

    public void modificarUsuario(UsuarioDTO dto){
        UsuarioEntity usuario = this.usuarioRepository.getById(dto.getId());

        usuario.setIdentificacion(dto.getIdentificacion());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setDireccion(dto.getDireccion());
        usuario.setTelefono(dto.getTelefono());
        usuario.setContrasena(dto.getContrasena());

        this.usuarioRepository.save(usuario);
    }

}