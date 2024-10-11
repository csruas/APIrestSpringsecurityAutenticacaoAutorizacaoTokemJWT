package med.voll.api.autenticacaoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.autenticacaoDTO.DadosAutenticacaoDTO;
import med.voll.api.domain.Usuario;
import med.voll.api.infra.security.DadosTokenJTWDTO;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private med.voll.api.infra.security.TokenService  tokenService;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {
	    var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
	    var authentication = manager.authenticate(authenticationToken);
	    
	    var tokenJTW = tokenService.gerarToken((Usuario) authentication.getPrincipal());
	    return ResponseEntity.ok(new DadosTokenJTWDTO(tokenJTW));
	}

}
