package estagio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SupervisorEstagio extends Usuario {
	@OneToMany(targetEntity = Estagio.class, mappedBy = "supervisorInstituicao", cascade = CascadeType.ALL)
	private List<Estagio> estagiosInstituicao = new ArrayList<Estagio>();

	@OneToMany(targetEntity = Estagio.class, mappedBy = "supervisorEmpresa", cascade = CascadeType.ALL)
	private List<Estagio> estagiosEmpresa = new ArrayList<Estagio>();

	public List<Estagio> getEstagiosInstituicao() {
		return estagiosInstituicao;
	}

	public void setEstagiosInstituicao(List<Estagio> estagiosInstituicao) {
		this.estagiosInstituicao = estagiosInstituicao;
	}

	public List<Estagio> getEstagiosEmpresa() {
		return estagiosEmpresa;
	}

	public void setEstagiosEmpresa(List<Estagio> estagiosEmpresa) {
		this.estagiosEmpresa = estagiosEmpresa;
	}
	
}
