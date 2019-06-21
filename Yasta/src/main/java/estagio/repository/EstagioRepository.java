package estagio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Aluno;
import estagio.model.Estagio;

@Repository
@Transactional
public interface EstagioRepository extends CrudRepository<Estagio, Long>{
	@Override
	List<Estagio> findAll();
	
	List<Estagio> findByAluno(Aluno aluno);
	
	@Query("SELECT e FROM Estagio e WHERE supervisorEmpresa.id = ?1 OR supervisorInstituicao.id = ?1")
	List<Estagio> findBySupervisor(Long id);
}
