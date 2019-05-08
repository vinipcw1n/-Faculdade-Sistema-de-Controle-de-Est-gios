package estagio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class SupervisorEstagio extends Usuario {
	@OneToMany
	private List<Estagio> estagios = new ArrayList<Estagio>();

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}
	
}
