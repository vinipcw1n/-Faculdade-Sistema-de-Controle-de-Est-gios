package estagio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Experiencia;

@Repository
@Transactional
public interface ExperienciaRepository extends CrudRepository<Experiencia, Long>{

}
