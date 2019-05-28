package estagio.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Experiencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String empresa;
	
	private String funcao;
	
	private LocalDate dataAdmissao;
	
	private LocalDate dataDemissao;
	
	private String tarefasRealizadas;
	
	@ManyToOne
	@JoinColumn(name = "curriculo_id")
	private Curriculo curriculo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	
	public void setDataAdmissao(String dataAdmissao) {
		String[] data = dataAdmissao.split("/");
		LocalDate dataFinal = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		this.dataAdmissao = dataFinal;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(String dataDemissao) {
		String[] data = dataDemissao.split("/");
		LocalDate dataFinal = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		this.dataDemissao = dataFinal;
	}

	public String getTarefasRealizadas() {
		return tarefasRealizadas;
	}

	public void setTarefasRealizadas(String tarefasRealizadas) {
		this.tarefasRealizadas = tarefasRealizadas;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	
	
}
