package med.voll.api.MedicoDTO;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.modelo.Especialidade;
import med.voll.api.medico.modelo.Medico;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
	
	public DadosDetalhamentoMedicoDTO (Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());

    }
}
