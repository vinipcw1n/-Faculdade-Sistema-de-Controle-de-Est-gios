package estagio.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	private String descricao;
	
	private LocalDate dataInicio;
	
	private LocalDate dataTermino;
	
	private int duracao;
	
	@ManyToOne
	private SupervisorEstagio supervisorEmpresa;

	@ManyToOne
	private SupervisorEstagio supervisorInstituicao;
	
	@OneToOne(cascade = CascadeType.ALL)
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

	public void setDataInicio(String dataInicio) {
		String[] data = dataInicio.split("/");
		LocalDate dataFinal = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		this.dataInicio = dataFinal;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		String[] data = dataTermino.split("/");
		LocalDate dataFinal = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		this.dataTermino = dataFinal;
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
