package estagio.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Estagio {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	private String descricao;
	
	private LocalDate dataInicio;
	
	private LocalDate dataTermino;
	
	private int duracao;
	
	@ManyToOne
	private SupervisorEstagio supervisorEmpresa;

	@ManyToOne
	private SupervisorEstagio supervisorInstituicao;
	
	@OneToOne
	private RelatorioEstagio relatorio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public RelatorioEstagio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelatorioEstagio relatorio) {
		this.relatorio = relatorio;
	}

	public void setSupervisorEmpresa(SupervisorEstagio supervisorEmpresa) {
		this.supervisorEmpresa = supervisorEmpresa;
	}
	
	public SupervisorEstagio getSupervisorEmpresa() {
		return supervisorEmpresa;
	}

	public void setSupervisorInstituicao(SupervisorEstagio supervisorInstituicao) {
		this.supervisorInstituicao = supervisorInstituicao;
	}
	
	public SupervisorEstagio getSupervisorInstituicao() {
		return supervisorInstituicao;
	}
}
