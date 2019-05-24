package estagio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Admin;
import estagio.model.Aluno;

@Repository
@Transactional
public interface AdminRepository extends CrudRepository<Admin, Long>{
	
	@Query("SELECT a FROM Admin a WHERE email = ?1")
	Admin findAdminByEmail(String email);
	
}
