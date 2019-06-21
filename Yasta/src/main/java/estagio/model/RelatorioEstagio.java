package estagio.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RelatorioEstagio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "estagio_id")
	private Estagio estagio;
	
	private LocalDate data;
	
	private String texto;
	
	private String observacoes;
	
	private boolean validado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(String data) {
		String[] dataN = data.split("/");
		LocalDate dataFinal = LocalDate.of(Integer.parseInt(dataN[2]), Integer.parseInt(dataN[1]), Integer.parseInt(dataN[0]));
		this.data = dataFinal;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
