package estagio.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Formacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String instituicao;
	
	private String curso;
	
	//@Column(columnDefinition = "NOT NULL")
	@NotNull
	private String nivel;
	
	private String situacao;
	
	private LocalDate dataInicio;
	
	private LocalDate dataTermino;
	
	@ManyToOne
	@JoinColumn(name = "curriculo_id")
	private Curriculo curriculo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Curriculo getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		String[] data = dataInicio.split("/");
		LocalDate dataFinal = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		this.dataInicio = dataFinal;
	}
	public LocalDate getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(String dataTermino) {
		if(!(dataTermino).equals("01/01/1800")) {
			String[] data = dataTermino.split("/");
			LocalDate dataFinal = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
			this.dataTermino = dataFinal;
		} else {
			this.dataTermino = null;
		}
	}
}
