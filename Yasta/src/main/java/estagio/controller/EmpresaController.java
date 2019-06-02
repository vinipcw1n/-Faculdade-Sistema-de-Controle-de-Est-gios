package estagio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.model.Aluno;
import estagio.model.Empresa;
import estagio.model.Vaga;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;
import estagio.repository.VagaRepository;

@Controller
public class EmpresaController {

	@Autowired
	EmpresaRepository empresaRepository;
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	VagaRepository vagaRepository;

}
