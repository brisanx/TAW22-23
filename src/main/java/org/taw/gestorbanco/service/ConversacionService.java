package org.taw.gestorbanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taw.gestorbanco.dao.ConversacionRepository;
import org.taw.gestorbanco.dao.EmpleadoRepository;
import org.taw.gestorbanco.dao.MensajeRepository;
import org.taw.gestorbanco.dao.UsuarioRepository;
import org.taw.gestorbanco.dto.EmpleadoDTO;
import org.taw.gestorbanco.dto.UsuarioDTO;
import org.taw.gestorbanco.entity.ConversacionEntity;
import org.taw.gestorbanco.entity.EmpleadoEntity;
import org.taw.gestorbanco.entity.MensajeEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<ConversacionEntity> listarGestorConversaciones(EmpleadoDTO empleadoDTO) {
        EmpleadoEntity empleado = this.empleadoRepository.findById(empleadoDTO.getIdGestor()).get();
        return this.conversacionRepository.findConversacionEntitiesByEmpleadoByEmpleadoIdGestor(empleado);
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
             conversacion.get().setNumeroMensaje(conversacion.get().getNumeroMensaje() + 1);
             this.conversacionRepository.save(conversacion.get());
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

    public List<MensajeEntity> listarMensajesUser(int idUser){
         List<ConversacionEntity> conversaciones = this.conversacionRepository.findConversacionEntitiesByUsuarioByUsuarioId(this.usuarioRepository.findById(idUser).get());
            List<MensajeEntity> mensajes = new ArrayList<>();

            for (ConversacionEntity conversacion : conversaciones) {
                mensajes.addAll(conversacion.getMensajesByIdConver());
            }

        return mensajes;
    }

     public List<EmpleadoEntity> todosLosAsistentes(){
        return this.empleadoRepository.todosLosAsistentes();
    }

    public List<UsuarioEntity> todosLosUsuarios(){
        return this.usuarioRepository.findAll();
    }
     public List<ConversacionEntity> buscarQueryConversacionesEntity(int empleadoId, int usuarioId, int estado, Timestamp fechaApertura, Timestamp fechaCierre, int numeroMensaje, String order) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ConversacionEntity> query = cb.createQuery(ConversacionEntity.class);
        Root<ConversacionEntity> conversacionEntityRoot = query.from(ConversacionEntity.class);
         List<Predicate> predicates = new ArrayList<>();
         if (empleadoId != 0) {
             predicates.add(cb.equal(conversacionEntityRoot.get("empleadoByEmpleadoIdGestor").get("idGestor"), empleadoId));
         }
         if (usuarioId != 0) {
             predicates.add(cb.equal(conversacionEntityRoot.get("usuarioByUsuarioId").get("id"), usuarioId));
         }
         if (estado != 0) {
             predicates.add(cb.equal(conversacionEntityRoot.get("estado"), estado));
         }
         if (fechaApertura != null) {
             predicates.add(cb.equal(conversacionEntityRoot.get("fechaApertura"), fechaApertura));
         }
            if (fechaCierre != null) {
                predicates.add(cb.equal(conversacionEntityRoot.get("fechaCierre"), fechaCierre));
            }
        if (numeroMensaje != -1) {
            predicates.add(cb.equal(conversacionEntityRoot.get("numeroMensaje"), numeroMensaje));
        }
        query.select(conversacionEntityRoot).where(predicates.toArray(new Predicate[]{})).orderBy(cb.asc(conversacionEntityRoot.get(order)));

         return entityManager.createQuery(query)
                 .getResultList();
    }
}


