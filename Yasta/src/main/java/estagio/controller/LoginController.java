package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.configuration.PopularBanco;
import estagio.model.Admin;
import estagio.model.Aluno;
import estagio.model.Curriculo;
import estagio.model.Empresa;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.CurriculoRepository;
import estagio.repository.EmpresaRepository;

@Controller
public class LoginController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurriculoRepository curriculoRepository;
	
//	@GetMapping("/")
//	public String index() {
//		return "login";
//	}
	
	@GetMapping("/")
	public String paginaInicial() {
		return "paginaInicial";
	}
	
	@GetMapping("/cadastrar/aluno")
	public ModelAndView cadastroAluno() {
		ModelAndView modelAndView = new ModelAndView("cadastroAluno");
		modelAndView.addObject("userObj", new Aluno());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar/empresa")
	public ModelAndView cadastroEmpresa() {
		ModelAndView modelAndView = new ModelAndView("cadastroEmpresa");
		modelAndView.addObject("userObj", new Empresa());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar/admin")
	public ModelAndView cadastroAdmin() {
		ModelAndView modelAndView = new ModelAndView("cadastroAdmin");
		modelAndView.addObject("userObj", new Admin());
		return modelAndView;
	}
	
	@PostMapping("**/salvar/aluno")
	public String salvarAluno(Aluno usuario) {
		Curriculo curriculo = new Curriculo();		
		curriculo = curriculoRepository.save(curriculo);
		
		usuario.setCurriculo(curriculo);
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
	
	@GetMapping("**/menu")
	public String menu() {
		return "menu";
	}
	
}
