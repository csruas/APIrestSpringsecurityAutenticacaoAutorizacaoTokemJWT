package med.voll.api.MedicoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.modelo.Especialidade;

public record DadosCadastroMedicoDTO(
		
		@NotBlank
        String nome,
        
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,
        
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        
        @NotNull
        Especialidade especialidade,

        @NotNull @Valid DadosEndereco endereco) {
}
