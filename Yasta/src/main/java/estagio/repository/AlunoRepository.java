package estagio.repository;

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
}
