package estagio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EmpresaController {
	@GetMapping("/cadastrar/vaga")
	public String meuCurriculo() {
		return "cadastroVaga";
	}
}
