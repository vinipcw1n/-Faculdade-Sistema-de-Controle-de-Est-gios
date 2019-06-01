package estagio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import estagio.model.Vaga;

@Repository
@Transactional
public interface VagaRepository extends CrudRepository<Vaga, Long>{
	List<Vaga> findAllByAberta(Boolean aberta);
}
