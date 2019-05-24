package estagio.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Curriculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany
	private List<Formacao> formacaoAcademica;
	
	@OneToMany
	private List<Experiencia> experienciaProfissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Formacao> getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(List<Formacao> formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}

	public List<Experiencia> getExperienciaProfissional() {
		return experienciaProfissional;
	}

	public void setExperienciaProfissional(List<Experiencia> experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}
	
	
}
