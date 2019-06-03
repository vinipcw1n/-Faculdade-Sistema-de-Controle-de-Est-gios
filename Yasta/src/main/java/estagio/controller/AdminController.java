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
import estagio.model.Usuario;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;
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

	@GetMapping("/gerenciarUsuarios")
	public ModelAndView gerenciarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("gerenciarUsuarios");

		List<Usuario> usuariosNaoAprovados = usuarioRepository.listAlunosPendente();
		usuariosNaoAprovados.addAll(usuarioRepository.listEmpresasPendente());

		List<Usuario> usuariosAprovados = usuarioRepository.listAlunosAprovados();
		usuariosAprovados.addAll(usuarioRepository.listEmpresasAprovados());
		usuariosAprovados.addAll((Collection<? extends Usuario>) adminRepository.findAll());

		modelAndView.addObject("usuariosNaoAprovadosObj", usuariosNaoAprovados);
		modelAndView.addObject("usuariosAprovadosObj", usuariosAprovados);
		return modelAndView;
	}

	@PostMapping("/usuario/editar")
	public ModelAndView editarUsuario(Usuario user, String acao) {
		ModelAndView modelAndView = new ModelAndView();
		if (acao.equals("modificar")) {
			if (!alunoRepository.findById(user.getId()).isEmpty()) {
				Optional<Aluno> aluno = alunoRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroAluno");
				modelAndView.addObject("userObj", aluno.get());
			} else if (!empresaRepository.findById(user.getId()).isEmpty()) {
				Optional<Empresa> empresa = empresaRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroEmpresa");
				modelAndView.addObject("userObj", empresa.get());
			} else if (!adminRepository.findById(user.getId()).isEmpty()) {
				Optional<Admin> admin = adminRepository.findById(user.getId());
				modelAndView = new ModelAndView("cadastroAdmin");
				modelAndView.addObject("userObj", admin.get());
			}
			modelAndView.addObject("edit", "");
		} else if (acao.equals("remover")) {
			if (!alunoRepository.findById(user.getId()).isEmpty()) {
				alunoRepository.deleteById(user.getId());
			} else if (!empresaRepository.findById(user.getId()).isEmpty()) {
				empresaRepository.deleteById(user.getId());
			} else if (!adminRepository.findById(user.getId()).isEmpty()) {
				adminRepository.deleteById(user.getId());
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
}
