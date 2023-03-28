package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {
}
