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
import estagio.model.Empresa;
import estagio.model.Usuario;
import estagio.model.Vaga;
import estagio.repository.AlunoRepository;
import estagio.repository.VagaRepository;

@Controller
public class VagaController {
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/vagas")
	public ModelAndView listarVagas() {
		ModelAndView modelAndView = new ModelAndView("vagas");
		List<Vaga> vagas = vagaRepository.findAllByAberta(true);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		
		modelAndView.addObject("vagasObj", vagas);
		modelAndView.addObject("aluno",aluno);

		return modelAndView;
	}
	
	@PostMapping("**/salvar/candidatura")
	public String salvarCandidatura(Long id) {
		Optional<Vaga> vaga = vagaRepository.findById(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Aluno aluno = alunoRepository.findAlunoByEmail(authentication.getName());
		
		if(vaga.get().getCandidatos().contains(aluno)) {
			vaga.get().getCandidatos().remove(aluno);
		} else {
			vaga.get().getCandidatos().add(aluno);
		}
		
		vagaRepository.save(vaga.get());
		
		return "redirect:/vagas";
	}
}
