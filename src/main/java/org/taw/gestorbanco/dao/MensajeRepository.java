package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.ConversacionEntity;
import org.taw.gestorbanco.entity.MensajeEntity;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {

    List<MensajeEntity> findMensajeEntitiesByConversacionByConversacionIdConver(ConversacionEntity idConversacion);

}