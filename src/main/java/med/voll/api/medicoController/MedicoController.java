package med.voll.api.medicoController;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.MedicoDTO.DadosAtualizacaoMedicoDTO;
import med.voll.api.MedicoDTO.DadosCadastroMedicoDTO;
import med.voll.api.MedicoDTO.DadosDetalhamentoMedicoDTO;
import med.voll.api.MedicoDTO.DadosListagemMedicoDTO;
import med.voll.api.medico.modelo.Medico;
import med.voll.api.medicoRepository.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
		repository.save(medico);

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
	}

	@GetMapping
	public ResponseEntity<org.springframework.data.domain.Page<DadosListagemMedicoDTO>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);

		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));

	}
}
