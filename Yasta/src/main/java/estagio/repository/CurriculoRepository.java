package estagio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Curriculo;

@Repository
@Transactional
public interface CurriculoRepository extends CrudRepository<Curriculo, Long>{
	
}
