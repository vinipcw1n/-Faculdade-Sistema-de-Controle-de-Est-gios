package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import estagio.model.Experiencia;
import estagio.model.Formacao;
import estagio.repository.ExperienciaRepository;
import estagio.repository.FormacaoRepository;

@Controller
public class AlunoController {
	@Autowired
	private FormacaoRepository formacaoRepository;
	private ExperienciaRepository experienciaRepository;
	
	@GetMapping("/curriculo")
	public String meuCurriculo() {
		return "curriculo";
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
