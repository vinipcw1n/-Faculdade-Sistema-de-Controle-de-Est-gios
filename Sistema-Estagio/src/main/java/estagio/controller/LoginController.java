package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import estagio.model.Aluno;
import estagio.repository.AlunoRepository;

@Controller
public class LoginController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@GetMapping("/cadastrar/aluno")
	public String cadastroAluno() {
		return "cadastroAluno";
	}
	
	@PostMapping("**/salvar/aluno")
	public String salvar(Aluno usuario) {
		alunoRepository.save(usuario);
		return "redirect:/";
	}
	
}
