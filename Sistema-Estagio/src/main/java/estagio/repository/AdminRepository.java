package estagio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Admin;

@Repository
@Transactional
public interface AdminRepository extends CrudRepository<Admin, Long>{
	
}
