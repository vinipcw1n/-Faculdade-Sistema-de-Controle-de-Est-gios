package estagio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Aluno;

@Repository
@Transactional
public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	
	@Query("SELECT a FROM Aluno a WHERE email = ?1")
	Aluno findAlunoByEmail(String email);
	
	@Query("Select a from Aluno a WHERE id = ?1")
	Aluno findAlunoById(Long id);
	
	@Query("SELECT v.candidatos FROM Vaga v RIGHT JOIN v.candidatos c WHERE v.id = ?1")
	List<Aluno> listarCandidatosVaga(Long idVaga);
}
