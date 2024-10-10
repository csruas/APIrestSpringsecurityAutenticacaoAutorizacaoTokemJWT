package med.voll.api.MedicoDTO;

import med.voll.api.medico.modelo.Especialidade;
import med.voll.api.medico.modelo.Medico;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
