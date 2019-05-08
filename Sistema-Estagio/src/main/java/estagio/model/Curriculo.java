package estagio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Curriculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String formacaoAcademica;
	
	private String experienciaProfissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(String formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}

	public String getExperienciaProfissional() {
		return experienciaProfissional;
	}

	public void setExperienciaProfissional(String experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}
	
	
}
