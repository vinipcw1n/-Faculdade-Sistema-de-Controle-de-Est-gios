package estagio.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import estagio.model.RelatorioEstagio;

@Repository
@Transactional
public interface RelatorioRepository extends CrudRepository<RelatorioEstagio, Long>{
	@Modifying(clearAutomatically = true)
	@Query("UPDATE RelatorioEstagio SET validado = true WHERE id = ?1")
	void validarRelatorio(Long id);
}