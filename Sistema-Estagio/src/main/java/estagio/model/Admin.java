package estagio.model;

import javax.persistence.Entity;

@Entity
public class Admin extends Usuario {

	private String rf;

	public String getRf() {
		return rf;
	}

	public void setRf(String rf) {
		this.rf = rf;
	}
	
}
