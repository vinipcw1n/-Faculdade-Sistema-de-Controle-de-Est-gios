package estagio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import estagio.model.Aluno;
import estagio.model.Vaga;

@Repository
@Transactional
public interface VagaRepository extends CrudRepository<Vaga, Long>{
	
	@Query("SELECT v FROM Vaga v WHERE v.aberta = ?1")
	List<Vaga> findAllByAberta(Boolean aberta);
	
	@Query("SELECT v FROM Vaga v WHERE v.empresa.id = ?1")
	List<Vaga> listarVagaByEmpresa(Long idEmpresa);
}
