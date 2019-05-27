package estagio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Formacao;

@Repository
@Transactional
public interface FormacaoRepository extends CrudRepository<Formacao, Long>{

}
