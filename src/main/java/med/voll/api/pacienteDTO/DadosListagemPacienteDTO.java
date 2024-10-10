package med.voll.api.pacienteDTO;

import med.voll.api.pacienteModelo.Paciente;

public record  DadosListagemPacienteDTO (Long id, String nome, String email, String cpf) {

    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
