package se.maokei.mserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Optional;

public class JwtUtil {
  //@Value("${app.jwt.secret}")
  private String secret;
  //@Value("${app.jwt.expiration}")
  private String expirationTime;
  private final String issuer = "mserver";

  public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
    return JWT.create()
            .withSubject("User Details")
            .withClaim("email", email)
            .withIssuedAt(new Date())
            .withIssuer(issuer)
            .sign(Algorithm.HMAC256(secret));
  }

  public Optional<String> getEmailFromToken(String token) {
    return  Optional.of(JWT.decode(token).getClaim("email").asString());
  }

  public Boolean validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm)
              .withSubject("User Details")
              .withIssuer(issuer)
              .build(); //Reusable verifier instance
      DecodedJWT jwt = verifier.verify(token);
    } catch (JWTVerificationException exception){
      //Invalid signature/claims
      return false;
    }
    return true;
  }

  public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
            .withSubject("User Details")
            .withIssuer(issuer)
            .build();
    DecodedJWT jwt = verifier.verify(token);
    return jwt.getClaim("email").asString();
  }
}
