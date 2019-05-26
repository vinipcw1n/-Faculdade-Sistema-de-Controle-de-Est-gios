package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import estagio.repository.AlunoRepository;

@Controller
public class AlunoController {
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/curriculo")
	public String meuCurriculo() {
		return "curriculo";
	}
}
