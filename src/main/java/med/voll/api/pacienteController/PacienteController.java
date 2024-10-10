package med.voll.api.pacienteController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.MedicoDTO.DadosCadastroMedicoDTO;
import med.voll.api.MedicoDTO.DadosDetalhamentoMedicoDTO;
import med.voll.api.medico.modelo.Medico;
import med.voll.api.pacienteDTO.DadosCadastroPacienteDTO;
import med.voll.api.pacienteDTO.DadosListagemPacienteDTO;
import med.voll.api.pacienteModelo.Paciente;
import med.voll.api.pacienteRepository.PacienteRepository;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
	
	@Autowired
	PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
		repository.save(paciente);

		return ResponseEntity.created(uri).body(new DadosListagemPacienteDTO(paciente));
	}
	
}