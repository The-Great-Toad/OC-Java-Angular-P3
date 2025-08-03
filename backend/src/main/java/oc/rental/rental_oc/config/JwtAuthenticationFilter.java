package oc.rental.rental_oc.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oc.rental.rental_oc.service.JwtService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String LOGGER_PREFIX = "[JwtAuthenticationFilter]";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_ = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("{} Processing request: {} {}", LOGGER_PREFIX, request.getMethod(), request.getRequestURI());
        final String authHeader = request.getHeader(AUTHORIZATION);

        /* Check Authorization header */
        if (Objects.isNull(authHeader) || !authHeader.startsWith(BEARER_)) {
            LOGGER.warn("{} No valid Authorization header found", LOGGER_PREFIX);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            LOGGER.info("{} Valid Authorization header found", LOGGER_PREFIX);
            /* Extract JWT from the Authorization header */
            final String jwt = authHeader.substring(BEARER_.length()).trim();
            final String userEmail = jwtService.extractUsername(jwt);
            LOGGER.info("{} Extracted user email: {}", LOGGER_PREFIX, userEmail);

            /* If userEmail is not null and no authentication is present, validate the JWT */
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (StringUtils.isNotBlank(userEmail) && Objects.isNull(authentication)) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                /* Check if the JWT is valid */
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    /* Create an authentication token and set it in the SecurityContext */
                    LOGGER.info("{} - Valid JWT - Setting authentication for user: {}", LOGGER_PREFIX, userEmail);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                } else {
                    LOGGER.warn("{} Invalid JWT for user: {} - Token expired: {}", LOGGER_PREFIX, userEmail, jwtService.isTokenExpired(jwt));
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception exception) {
            LOGGER.error("{} Exception occurred while processing JWT: {}", LOGGER_PREFIX, exception.getMessage(), exception);
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
