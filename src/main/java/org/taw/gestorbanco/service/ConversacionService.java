package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.ConversacionRepository;
import org.taw.gestorbanco.dao.EmpleadoRepository;
import org.taw.gestorbanco.dao.MensajeRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.ConversacionEntity;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.MensajeEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ConversacionService {

     @Autowired
        protected ConversacionRepository conversacionRepository;
     @Autowired
      protected MensajeRepository mensajeRepository;
     @Autowired
        protected UsuarioRepository usuarioRepository;

     @Autowired
       protected EmpleadoRepository empleadoRepository;
      //US-23 y US-24
     public ConversacionEntity crearConversacion(int idUsuario, int gestorId){
         ConversacionEntity conversacion = new ConversacionEntity();
         conversacion.setEstado(1);
         conversacion.setNumeroMensaje(0);
         conversacion.setUsuarioByUsuarioId(usuarioRepository.findById(idUsuario).get());
            conversacion.setEmpleadoByEmpleadoIdGestor(empleadoRepository.findById(gestorId).get());
         conversacion.setFechaApertura(new Timestamp(System.currentTimeMillis()));
         conversacionRepository.save(conversacion);
         return conversacion;
     }

     public ConversacionEntity recuperaConversacion(int idConversacion){
         Optional<ConversacionEntity> conversacion = this.conversacionRepository.findById(idConversacion);
            if (conversacion.isPresent()) {
                return conversacion.get();
            } else {
                throw new RuntimeException("Conversacion no encontrada");
            }
     }

     public ConversacionEntity cerrarConversacion(int idConversacion){
         Optional<ConversacionEntity> conversacion = this.conversacionRepository.findById(idConversacion);
         if (conversacion.isPresent()) {
             conversacion.get().setFechaCierre(new Timestamp(System.currentTimeMillis()));
             conversacion.get().setEstado(2);
                return this.conversacionRepository.save(conversacion.get());
         } else {
             throw new RuntimeException("Conversacion no encontrada");
         }
     }
      public List<ConversacionEntity> listarConversaciones(UsuarioDTO usuarioDTO) {
         UsuarioEntity usuario = this.usuarioRepository.findById(usuarioDTO.getId()).get();
          return this.conversacionRepository.findConversacionEntitiesByUsuarioByUsuarioId(usuario);
      }

     public MensajeEntity crearMensaje(int idConversacion, String sender, String mensaje){
         MensajeEntity mensajeInternal = new MensajeEntity();
         mensajeInternal.setTexto(mensaje);
         mensajeInternal.setSender(sender);
         mensajeInternal.setLongitud(mensaje.length() );
         mensajeInternal.setFecha(new Timestamp(System.currentTimeMillis()));
         Optional<ConversacionEntity> conversacion = this.conversacionRepository.findById(idConversacion);
         if (conversacion.isPresent()) {
             mensajeInternal.setConversacionByConversacionIdConver(conversacion.get());
         } else {
             throw new RuntimeException("Conversacion no encontrada");
         }
         MensajeEntity mensajeCreate = this.mensajeRepository.save(mensajeInternal);
         return mensajeCreate;
     }

     public List<MensajeEntity> listarMensajes(int idConversacion){
         ConversacionEntity conversacion = this.conversacionRepository.findById(idConversacion).get();
         return this.mensajeRepository.findMensajeEntitiesByConversacionByConversacionIdConver(conversacion);
     }

     public List<EmpleadoEntity> getTodosLosGestores(){
         return this.empleadoRepository.todosLosGestores();
     }
}


