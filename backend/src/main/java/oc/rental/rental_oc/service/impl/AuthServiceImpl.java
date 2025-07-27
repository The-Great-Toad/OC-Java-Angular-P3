package oc.rental.rental_oc.service.impl;

import oc.rental.rental_oc.dto.auth.AuthResponse;
import oc.rental.rental_oc.dto.auth.RegisterRequest;
import oc.rental.rental_oc.entity.User;
import oc.rental.rental_oc.mapper.UserMapper;
import oc.rental.rental_oc.repository.UserRepository;
import oc.rental.rental_oc.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private static final String LOGGER_PREFIX = "[AuthService]";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

    private AuthResponse generateToken(User user) {
        return new AuthResponse("jwt"); // TODO: implement JWT generation logic
    }
}
