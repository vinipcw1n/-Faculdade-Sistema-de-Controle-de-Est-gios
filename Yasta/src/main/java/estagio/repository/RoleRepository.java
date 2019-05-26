package estagio.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import estagio.model.Role;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long>{

}
