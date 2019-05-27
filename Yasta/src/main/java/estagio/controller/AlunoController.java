package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.model.Aluno;
import estagio.model.Curriculo;
import estagio.model.Experiencia;
import estagio.model.Formacao;
import estagio.repository.AlunoRepository;
import estagio.repository.CurriculoRepository;
import estagio.repository.ExperienciaRepository;
import estagio.repository.FormacaoRepository;

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
	
	@GetMapping("/curriculo")
	public ModelAndView meuCurriculo() {
		ModelAndView modelAndView = new ModelAndView("curriculo");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		modelAndView.addObject("alunoObj", aluno);
		return modelAndView;
	}
	
	
	@PostMapping("/salvar/Formacao")
	public String salvarFormacao(Formacao formacao) {
		formacaoRepository.save(formacao);
		return "curriculo";
	}
	
	@PostMapping("/salvar/Experiencia")
	public String salvarExperiencia(Experiencia experiencia) {
		experienciaRepository.save(experiencia);
		return "curriculo";
	}
}
