package oc.rental.rental_oc.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import oc.rental.rental_oc.dto.auth.AuthResponse;
import oc.rental.rental_oc.exception.TokenGenerationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    @Value("${jwt.expiration.time}")
    private int tokenExpirationTime;

    private JWSSigner signer;

    @PostConstruct
    public void initSigner() throws KeyLengthException {
        this.signer = new MACSigner(secretKey);
    }

    /**
     * Generates a JWT token for the given user email.
     * <a href="https://connect2id.com/products/nimbus-jose-jwt/examples/jwt-with-hmac">Nimbus-jose-jwt library documentation</a>
     *
     * @param userEmail the email of the user for whom the token is generated
     * @return AuthResponse containing the generated JWT token
     */
    public AuthResponse generateToken(String userEmail) {
        try {
            /* Prepare JWT with claims set */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userEmail)
                    .issuer(issuer)
                    .audience(audience)
                    .issueTime(new Date())
                    .notBeforeTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + tokenExpirationTime))
                    .jwtID(UUID.randomUUID().toString())
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
            signedJWT.sign(signer);

            return new AuthResponse(signedJWT.serialize());

        } catch (JOSEException ex) {
            throw new TokenGenerationException("Failed to generate JWT token: " + ex.getMessage(), ex);
        }
    }
}
