package estagio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Aluno;
import estagio.model.Empresa;

@Repository
@Transactional
public interface EmpresaRepository  extends CrudRepository<Empresa, Long>{
	@Query("SELECT e FROM Empresa e WHERE email = ?1")
	Empresa findEmpresaByEmail(String email);
}
