package org.taw.gestorbanco.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taw.gestorbanco.entity.ConversacionEntity;
import org.taw.gestorbanco.entity.UsuarioEntity;

import java.util.List;


public interface ConversacionRepository extends JpaRepository<ConversacionEntity, Integer> {

    List<ConversacionEntity> findConversacionEntitiesByUsuarioByUsuarioId(UsuarioEntity Usuario);
}