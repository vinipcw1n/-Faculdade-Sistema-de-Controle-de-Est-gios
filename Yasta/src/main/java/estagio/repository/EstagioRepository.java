package estagio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Estagio;

@Repository
@Transactional
public interface EstagioRepository extends CrudRepository<Estagio, Long>{
	@Override
	List<Estagio> findAll();
}
