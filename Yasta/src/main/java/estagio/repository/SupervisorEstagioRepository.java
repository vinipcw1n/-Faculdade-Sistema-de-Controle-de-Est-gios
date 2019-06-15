package estagio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.SupervisorEstagio;

@Repository
@Transactional
public interface SupervisorEstagioRepository extends CrudRepository<SupervisorEstagio,Long>{
	@Query("SELECT e FROM SupervisorEstagio e WHERE email = ?1")
	SupervisorEstagio findSupervisorEstagioByEmail(String email);
}
