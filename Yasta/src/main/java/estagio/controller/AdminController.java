package estagio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.model.Aluno;
import estagio.model.Empresa;
import estagio.model.Usuario;
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

	@GetMapping("/gerenciarUsuarios")
	public ModelAndView gerenciarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("gerenciarUsuarios");
		List<Usuario> usuariosNaoAprovados = usuarioRepository.listAlunosPendente();
		usuariosNaoAprovados.addAll(usuarioRepository.listEmpresasPendente());
		List<Usuario> usuariosAprovados = usuarioRepository.listAlunosAprovados();
		usuariosAprovados.addAll(usuarioRepository.listEmpresasAprovados());
		modelAndView.addObject("usuariosNaoAprovadosObj", usuariosNaoAprovados);
		modelAndView.addObject("usuariosAprovadosObj", usuariosAprovados);
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
