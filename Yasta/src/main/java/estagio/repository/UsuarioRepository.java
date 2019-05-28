package estagio.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import estagio.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	@Query("select a from Aluno a WHERE aprovado = false")
	List<Usuario> listAlunosPendente();
	
	@Query("select e from Empresa e WHERE aprovado = false")
	List<Usuario> listEmpresasPendente();
	
	@Query("Select a from Aluno a WHERE aprovado = true")
	List<Usuario> listAlunosAprovados();
	
	@Query("Select e from Empresa e WHERE aprovado = true")
	List<Usuario> listEmpresasAprovados();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Aluno SET aprovado = true WHERE email = ?1")
	void aprovarAluno(String email);
	
	@Modifying(clearAutomatically = true)
	@Query("update Empresa set aprovado = true where email = ?1")
	void aprovarEmpresa(String email);
}
