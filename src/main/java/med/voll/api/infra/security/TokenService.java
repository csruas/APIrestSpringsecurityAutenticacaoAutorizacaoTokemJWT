package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.Usuario;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	public String gerarToken(Usuario usuario) {
		System.out.println(secret);
		try {
		    
			var algoritimo = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API voll.med")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpiracao())
		        .withClaim("id", usuario.getId())
		        .sign(algoritimo);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("erro ao gerar toke, jwt", exception);
		}
	}

	private Instant dataExpiracao() {
		// TODO Auto-generated method stub
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
