package estagio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.model.Aluno;
import estagio.model.Curriculo;
import estagio.model.Estagio;
import estagio.model.Experiencia;
import estagio.model.Formacao;
import estagio.model.RelatorioEstagio;
import estagio.model.SupervisorEstagio;
import estagio.repository.AlunoRepository;
import estagio.repository.CurriculoRepository;
import estagio.repository.EstagioRepository;
import estagio.repository.ExperienciaRepository;
import estagio.repository.FormacaoRepository;
import estagio.repository.RelatorioRepository;
import estagio.repository.SupervisorEstagioRepository;

@Controller
public class AlunoController {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private CurriculoRepository curriculoRepository;
	@Autowired
	private FormacaoRepository formacaoRepository;
	@Autowired
	private ExperienciaRepository experienciaRepository;
	@Autowired
	private EstagioRepository estagioRepository;
	@Autowired
	private RelatorioRepository relatorioRepository;
	@Autowired
	private SupervisorEstagioRepository supervisorEstagioRepository;
	
	@GetMapping("/curriculo")
	public ModelAndView meuCurriculo() {
		ModelAndView modelAndView = new ModelAndView("curriculo");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		modelAndView.addObject("alunoObj", aluno);
		if(aluno.getCurriculo().getFormacaoAcademica() != null) {
			modelAndView.addObject("formacoes", aluno.getCurriculo().getFormacaoAcademica());
		}
		if(aluno.getCurriculo().getExperienciaProfissional() != null) {
			modelAndView.addObject("experiencias", aluno.getCurriculo().getExperienciaProfissional());
		}
		return modelAndView;
	}
	
	@PostMapping("**/salvar/formacao")
	public String salvarFormacao(Formacao formacao) {
		System.out.println("a");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		formacao.setCurriculo(aluno.getCurriculo());
		formacaoRepository.save(formacao);
		return "redirect:/curriculo";
	}
	
	@PostMapping("**/salvar/experiencia")
	public String salvarExperiencia(Experiencia experiencia) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		experiencia.setCurriculo(aluno.getCurriculo());
		experienciaRepository.save(experiencia);
		return "redirect:/curriculo";
	}
	
	@GetMapping("/meusEstagios")
	public ModelAndView meusEstagios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		SupervisorEstagio supervisor = supervisorEstagioRepository.findSupervisorEstagioByEmail(authentication.getName());
		
		ModelAndView modelAndView = new ModelAndView("meusEstagios");
		
		List<Estagio> estagios;
		
		if(aluno != null) {
			estagios = estagioRepository.findByAluno(aluno);
		}else{
			estagios = estagioRepository.findBySupervisor(supervisor.getId());
		}
		modelAndView.addObject("estagiosObj", estagios);
		
		return modelAndView;
	}
	
	@PostMapping("**/salvar/relatorio")
	public String salvarRelatorio(RelatorioEstagio relatorio, String source) {
		relatorioRepository.save(relatorio);
		Optional<Estagio> estagioOpt = estagioRepository.findById(relatorio.getEstagio().getId());
		Estagio estagio = estagioOpt.get();
		estagio.setRelatorio(relatorio);
		estagioRepository.save(estagio);
		return "redirect:/" + source;
	}
	
	@PostMapping("/cadastrar/relatorio")
	public ModelAndView cadastrarSupervisor(Estagio estagio, String acao, String source) {
		ModelAndView modelAndView = new ModelAndView("cadastroRelatorio");
		RelatorioEstagio relatorio = new RelatorioEstagio();
		relatorio.setEstagio(estagio);
		modelAndView.addObject("relatorioObj",relatorio);
		modelAndView.addObject("source", source);
		modelAndView.addObject("acao", acao);
		return modelAndView;
	}
	
	@PostMapping("relatorio/editar")
	public ModelAndView editarEstagio(RelatorioEstagio relatorio, String acao, String source) {		
		ModelAndView modelAndView;
		relatorio = relatorioRepository.findById(relatorio.getId()).get();
		
		if(acao.equals("remover")) {
			Estagio estagio = estagioRepository.findById(relatorio.getEstagio().getId()).get();
			estagio.setRelatorio(null);
			estagioRepository.save(estagio);
			relatorioRepository.deleteById(relatorio.getId());
			modelAndView = new ModelAndView("redirect:/gerenciarEstagios");
		} else if(acao.equals("validar")) {
			relatorioRepository.validarRelatorio(relatorio.getId());
			modelAndView = new ModelAndView("redirect:/gerenciarEstagios");
		} else {

			modelAndView = new ModelAndView("cadastroRelatorio");
			modelAndView.addObject("relatorioObj", relatorio);
			modelAndView.addObject("acao", acao);
			modelAndView.addObject("source", source);
		}
		
		return modelAndView;
	}
}
