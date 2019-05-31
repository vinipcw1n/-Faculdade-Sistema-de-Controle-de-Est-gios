package estagio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VagaController {
	@GetMapping("/vagas")
	public String listarVagas() {
		return "vagas";
	}
}
