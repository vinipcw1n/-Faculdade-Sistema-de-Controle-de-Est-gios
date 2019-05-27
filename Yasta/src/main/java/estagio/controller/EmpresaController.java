package estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import estagio.model.Empresa;
import estagio.model.Vaga;
import estagio.repository.EmpresaRepository;
import estagio.repository.VagaRepository;

@Controller
public class EmpresaController {

	@Autowired
	EmpresaRepository empresaRepository;
	
	@Autowired
	VagaRepository vagaRepository;
	
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
		
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
}
