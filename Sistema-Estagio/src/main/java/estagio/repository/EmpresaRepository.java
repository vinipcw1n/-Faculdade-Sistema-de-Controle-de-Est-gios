package estagio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Empresa;

@Repository
@Transactional
public interface EmpresaRepository  extends CrudRepository<Empresa, Long>{

}
