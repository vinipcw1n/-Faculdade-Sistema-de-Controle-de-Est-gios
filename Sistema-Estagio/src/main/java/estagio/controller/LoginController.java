package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import estagio.model.Admin;
import estagio.model.Aluno;
import estagio.model.Empresa;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;

@Controller
public class LoginController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	@GetMapping("/paginaInicial")
	public String paginaInicial() {
		return "paginaInicial";
	}
	
	@GetMapping("/cadastrar/aluno")
	public String cadastroAluno() {
		return "cadastroAluno";
	}
	
	@GetMapping("/cadastrar/empresa")
	public String cadastroEmpresa() {
		return "cadastroEmpresa";
	}
	
	@GetMapping("/cadastrar/admin")
	public String cadastroAdmin() {
		return "cadastroAdmin";
	}
	
	@PostMapping("**/salvar/aluno")
	public String salvarAluno(Aluno usuario) {
		alunoRepository.save(usuario);
		return "redirect:/";
	}
	
	@PostMapping("**/salvar/empresa")
	public String salvarEmpresa(Empresa usuario) {
		empresaRepository.save(usuario);
		return "redirect:/";
	}
	
	@PostMapping("**/salvar/admin")
	public String salvarAdmin(Admin admin) {
		adminRepository.save(admin);
		return "redirect:/";
	}
	
}
