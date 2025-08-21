package oc.rental.rental_oc.service.impl;

import jakarta.annotation.PostConstruct;
import oc.rental.rental_oc.constant.ErrorMessages;
import oc.rental.rental_oc.constant.ValidationMessages;
import oc.rental.rental_oc.dto.UserDto;
import oc.rental.rental_oc.dto.response.AuthResponse;
import oc.rental.rental_oc.dto.request.LoginRequest;
import oc.rental.rental_oc.dto.request.RegisterRequest;
import oc.rental.rental_oc.entity.User;
import oc.rental.rental_oc.mapper.UserMapper;
import oc.rental.rental_oc.repository.UserRepository;
import oc.rental.rental_oc.service.AuthService;
import oc.rental.rental_oc.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private static final String LOGGER_PREFIX = "[AuthService]";

    @Value("${spring.security.user.name}")
    private String name;
    @Value("${spring.security.user.password}")
    private String password;
    @Value("${spring.security.user.roles}")
    private String roles;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private String encodedPassword;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostConstruct
    public void init() {
        encodedPassword = passwordEncoder.encode(password);
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        LOGGER.info("{} Registering user with email: {}", LOGGER_PREFIX, registerRequest.email());

        /* Save user */
        User registeredUser = userMapper.mapToUser(registerRequest);
        userRepository.save(registeredUser);
        LOGGER.debug("{} Registered user details: {}", LOGGER_PREFIX, registeredUser);
        LOGGER.info("{} User registered successfully: {}", LOGGER_PREFIX, registerRequest.email());

        /* Generate JWT */
        return generateToken(registeredUser);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        LOGGER.info("{} Login attempt for email: {}", LOGGER_PREFIX, loginRequest.email());

        User user = userRepository
                .findByEmail(loginRequest.email())
                .orElseThrow(() -> new BadCredentialsException(ValidationMessages.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            LOGGER.warn("{} Failed login attempt for email: {}", LOGGER_PREFIX, loginRequest.email());
            throw new BadCredentialsException(ValidationMessages.INVALID_CREDENTIALS);
        }

        return generateToken(user);
    }

    @Override
    public UserDto getUserDetails(Principal principal) {
        String email = principal.getName();
        LOGGER.info("{} Retrieving user details for email: {}", LOGGER_PREFIX, email);

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND));

        return userMapper.mapToUserDto(user);
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        return userRepository.findUserIdByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND));
    }

    @Override
    public UserDto getUserById(Integer id) {
        LOGGER.info("{} Retrieving user by ID: {}", LOGGER_PREFIX, id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("{} User not found with ID: {}", LOGGER_PREFIX, id);
                    return new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND);
                });

        LOGGER.info("{} User found: {}", LOGGER_PREFIX, user);
        return userMapper.mapToUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (name.equals(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(name)
                    .password(encodedPassword)
                    .roles(roles.split(","))
                    .build();
        } else {
            return userRepository
                    .findByEmail(username)
                    .orElseThrow(() -> {
                        LOGGER.warn("{} User not found with email: {}", LOGGER_PREFIX, username);
                        return new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND);
                    });
        }
    }

    private AuthResponse generateToken(User user) {
        LOGGER.debug("{} Generating JWT token for user: {}", LOGGER_PREFIX, user.getEmail());
        return jwtService.generateToken(user.getEmail());
    }
}
