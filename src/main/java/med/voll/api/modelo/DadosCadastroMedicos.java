package med.voll.api.modelo;

import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedicos (String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco){

}
