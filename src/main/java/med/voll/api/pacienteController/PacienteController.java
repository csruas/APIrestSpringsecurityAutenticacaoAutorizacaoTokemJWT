package med.voll.api.pacienteController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
	
	@GetMapping
	public String olaMundo(String json) {
		return "Ola mundo spring";
	}
	
}