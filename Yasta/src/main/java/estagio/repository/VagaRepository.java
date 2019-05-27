package estagio.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import estagio.model.Vaga;

@Repository
@Transactional
public interface VagaRepository extends CrudRepository<Vaga, Long>{

}
