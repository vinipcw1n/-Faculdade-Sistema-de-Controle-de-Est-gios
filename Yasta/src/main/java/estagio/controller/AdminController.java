package estagio.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.model.Admin;
import estagio.model.Aluno;
import estagio.model.Empresa;
import estagio.model.Estagio;
import estagio.model.SupervisorEstagio;
import estagio.model.Usuario;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;
import estagio.repository.EstagioRepository;
import estagio.repository.SupervisorEstagioRepository;
import estagio.repository.UsuarioRepository;

@Controller
public class AdminController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EstagioRepository estagioRepository;
	
	@Autowired
	SupervisorEstagioRepository supervisorEstagioRepository;

	@GetMapping("/gerenciarUsuarios")
	public ModelAndView gerenciarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("gerenciarUsuarios");

		List<Usuario> usuariosNaoAprovados = usuarioRepository.listAlunosPendente();
		usuariosNaoAprovados.addAll(usuarioRepository.listEmpresasPendente());

		List<Usuario> usuariosAprovados = usuarioRepository.listAlunosAprovados();
		usuariosAprovados.addAll(usuarioRepository.listEmpresasAprovados());
		usuariosAprovados.addAll((Collection<? extends Usuario>) adminRepository.findAll());
		usuariosAprovados.addAll((Collection<? extends Usuario>) supervisorEstagioRepository.findAll());
		
		modelAndView.addObject("usuariosNaoAprovadosObj", usuariosNaoAprovados);
		modelAndView.addObject("usuariosAprovadosObj", usuariosAprovados);
		return modelAndView;
	}
	
	@GetMapping("/gerenciarEstagios")
	public ModelAndView gerenciarEstagios() {
		ModelAndView modelAndView = new ModelAndView("gerenciarEstagios");
		
		List<Estagio> estagios = estagioRepository.findAll();
		
		modelAndView.addObject("estagiosObj", estagios);
		
		return modelAndView;
	}

	@PostMapping("/usuario/editar")
	public ModelAndView editarUsuario(Usuario user, String acao) {
		ModelAndView modelAndView = new ModelAndView();
		if (acao.equals("modificar")) {
			if (alunoRepository.findById(user.getId()).isPresent()) {
				Optional<Aluno> aluno = alunoRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroAluno");
				modelAndView.addObject("userObj", aluno.get());
			} else if (empresaRepository.findById(user.getId()).isPresent()) {
				Optional<Empresa> empresa = empresaRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroEmpresa");
				modelAndView.addObject("userObj", empresa.get());
			} else if (adminRepository.findById(user.getId()).isPresent()) {
				Optional<Admin> admin = adminRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroAdmin");
				modelAndView.addObject("userObj", admin.get());
			} else if (supervisorEstagioRepository.findById(user.getId()).isPresent()) {
				Optional<SupervisorEstagio> supervisor = supervisorEstagioRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroSupervisor");
				modelAndView.addObject("userObj", supervisor.get());
			}
			modelAndView.addObject("edit", "");
		} else if (acao.equals("remover")) {
			if (alunoRepository.findById(user.getId()).isPresent()) {
				alunoRepository.deleteById(user.getId());
			} else if (empresaRepository.findById(user.getId()).isPresent()) {
				empresaRepository.deleteById(user.getId());
			} else if (adminRepository.findById(user.getId()).isPresent()) {
				adminRepository.deleteById(user.getId());
			} else if (supervisorEstagioRepository.findById(user.getId()).isPresent()) {
				supervisorEstagioRepository.deleteById(user.getId());
			}
			modelAndView = new ModelAndView("redirect:/gerenciarUsuarios");
		}
		return modelAndView;
	}

	@PostMapping("/aprovarUsuario")
	public String aprovarUsuario(Usuario usuario) {
		if (!(alunoRepository.findAlunoByEmail(usuario.getEmail()) == (null))) {
			usuarioRepository.aprovarAluno(usuario.getEmail());
		} else if (!(empresaRepository.findEmpresaByEmail(usuario.getEmail()) == (null))) {
			usuarioRepository.aprovarEmpresa(usuario.getEmail());
		}
		return "redirect:/gerenciarUsuarios";
	}
	
	@GetMapping("/cadastrar/estagio")
	public ModelAndView cadastrarEstagio() {
		ModelAndView modelAndView = new ModelAndView("cadastroEstagio");
		modelAndView.addObject("estagioObj", new Estagio());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar/supervisor")
	public ModelAndView cadastrarSupervisor() {
		ModelAndView modelAndView = new ModelAndView("cadastroSupervisor");
		modelAndView.addObject("userObj", new SupervisorEstagio());
		return modelAndView;
	}
	
	@PostMapping("**/salvar/supervisor")
	public String salvarSupervisor(SupervisorEstagio supervisor) {
		supervisorEstagioRepository.save(supervisor);
		return("redirect:/gerenciarUsuarios");
	}
	
	@PostMapping("**/salvar/estagio")
	public String salvarEstagio(Estagio estagio) {
		estagio.setSupervisorEmpresa(supervisorEstagioRepository.findSupervisorEstagioByEmail(estagio.getSupervisorEmpresa().getEmail()));
		estagio.setSupervisorInstituicao(supervisorEstagioRepository.findSupervisorEstagioByEmail(estagio.getSupervisorInstituicao().getEmail()));
		estagio.setAluno(alunoRepository.findAlunoByEmail(estagio.getAluno().getEmail()));
		estagio.setEmpresa(empresaRepository.findEmpresaByEmail(estagio.getEmpresa().getEmail()));
		
		estagioRepository.save(estagio);
		return("redirect:/gerenciarEstagios");
	}
	
	@PostMapping("estagio/editar")
	public ModelAndView editarEstagio(Estagio estagio, String acao) {
		ModelAndView modelAndView = new ModelAndView();
		if (acao.equals("modificar")) {
			Optional<Estagio> estagioOpt = estagioRepository.findById(estagio.getId());
			modelAndView = new ModelAndView("cadastroEstagio");
			modelAndView.addObject("estagioObj", estagioOpt.get());
		}else {
			estagioRepository.deleteById(estagio.getId());
			modelAndView = new ModelAndView("redirect:/gerenciarEstagios");
		}
		return modelAndView;
	}
}
