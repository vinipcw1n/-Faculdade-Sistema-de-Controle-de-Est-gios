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
import estagio.model.Usuario;
import estagio.model.Vaga;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;
import estagio.repository.VagaRepository;

@Controller
public class VagaController {
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
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
	
	@PostMapping("/vaga")
	public ModelAndView verCurriculoAluno(Vaga vaga) {
		List <Aluno> aluno = alunoRepository.listarCandidatosVaga(vaga.getId());
		ModelAndView modelAndView = new ModelAndView("gerenciarCandidatos");
		modelAndView.addObject("candidatosObj", aluno);
		return modelAndView;
	} 
	
	@PostMapping("/vaga/editar")
	public ModelAndView editarVaga(Vaga vaga){
		ModelAndView modelAndView = new ModelAndView("cadastroVaga");
		Optional<Vaga> findVaga = vagaRepository.findById(vaga.getId());
		modelAndView.addObject("vagaObj", findVaga.get());
		modelAndView.addObject("edit", "");
		return modelAndView;
	}
	
	@GetMapping("/minhasVagas")
	public ModelAndView minhasVagas(){
		ModelAndView modelAndView = new ModelAndView("gerenciarVagas");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Empresa empresa = empresaRepository.findEmpresaByEmail(authentication.getName());
		List<Vaga> vagas = vagaRepository.listarVagaByEmpresa(empresa.getId());
		
		modelAndView.addObject("vagasObj", vagas);

		return modelAndView;
	}
	
	
	@GetMapping("/curriculo/{id}")
	public ModelAndView gerenciarCandidatos(@PathVariable("id") Long idAluno) {
		Aluno aluno = alunoRepository.findAlunoById(idAluno);
		aluno = alunoRepository.findAlunoByEmail(aluno.getEmail());
		ModelAndView modelAndView = new ModelAndView("curriculo");
		modelAndView.addObject("alunoObj", aluno);
		if(aluno.getCurriculo().getFormacaoAcademica() != null) {
			modelAndView.addObject("formacoes", aluno.getCurriculo().getFormacaoAcademica());
		}
		if(aluno.getCurriculo().getExperienciaProfissional() != null) {
			modelAndView.addObject("experiencias", aluno.getCurriculo().getExperienciaProfissional());
		}
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
	
	@GetMapping("/cadastrar/vaga")
	public ModelAndView cadastrarVaga() {
		ModelAndView modelAndView = new ModelAndView("cadastroVaga");
		modelAndView.addObject("vagaObj", new Vaga());
		return modelAndView;
	}
	
	@PostMapping("**/salvar/vaga")
	public ModelAndView salvarVaga(Vaga vaga){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Empresa empresa = empresaRepository.findEmpresaByEmail(authentication.getName());
		vaga.setEmpresa(empresa);
		
		vagaRepository.save(vaga);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/minhasVagas");
		return modelAndView;
	}
	
	@PostMapping("**/abrir/vaga")
	public String abrirVaga(Long id) {
		Vaga vaga = vagaRepository.findById(id).get();
		
		if(vaga.isAberta()) {
			vaga.setAberta(false);
		} else {
			vaga.setAberta(true);
		}
		
		vagaRepository.save(vaga);
		
		return "redirect:/minhasVagas";
	}
	
}
