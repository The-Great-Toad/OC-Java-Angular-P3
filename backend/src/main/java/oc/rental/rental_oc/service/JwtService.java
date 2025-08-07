package oc.rental.rental_oc.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import oc.rental.rental_oc.dto.response.AuthResponse;
import oc.rental.rental_oc.exception.TokenGenerationException;
import oc.rental.rental_oc.exception.TokenValidationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);
    private static final String LOGGER_PREFIX = "[JwtService]";

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    @Value("${jwt.expiration.time}")
    private int tokenExpirationTime;

    private JWSSigner signer;
    private JWSVerifier verifier;

    @PostConstruct
    public void initSigner() throws JOSEException {
        this.signer = new MACSigner(secretKey);
        this.verifier = new MACVerifier(secretKey);
        LOGGER.info("{} - JWT signer and verifier initialized with secret key", LOGGER_PREFIX);
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

    /**
     * Validates the JWT token against the provided user details.
     * It checks if the token is signed correctly, if the username matches, and if the token is not expired.
     *
     * @param token the JWT token to validate
     * @param user the user details to match against the token
     * @return true if the token is valid, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails user) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            return signedJWT.verify(verifier) &&
                    StringUtils.equals(extractUsername(token), user.getUsername()) &&
                    !isTokenExpired(token);

        } catch (Exception e) {
            LOGGER.error("{} Token validation failed: {}", LOGGER_PREFIX, e.getMessage());
            return false;
        }
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param token the JWT token from which to extract the username
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();

        } catch (ParseException ex) {
            throw new TokenValidationException("Failed to parse JWT token: " + ex.getMessage(), ex);
        }
    }

    /**
     * Checks if the token is expired.
     *
     * @param token the JWT token to check
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token the JWT token from which to extract the expiration date
     * @return the expiration date extracted from the token
     */
    private Date extractExpiration(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getExpirationTime();

        } catch (ParseException ex) {
            throw new TokenValidationException("Failed to parse JWT token: " + ex.getMessage(), ex);
        }
    }
}
