package oc.rental.rental_oc.mapper;

import oc.rental.rental_oc.dto.auth.RegisterRequest;
import oc.rental.rental_oc.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Map RegisterRequest to User entity.
     *
     * @param registerRequest the registration request containing user details
     * @return a User entity populated with the registration details
     */
    public User mapToUser(RegisterRequest registerRequest) {
        return new User()
                .addName(registerRequest.name())
                .addEmail(registerRequest.email())
                .addPassword(passwordEncoder.encode(registerRequest.password()))
                .addCreationDate()
                .addUpdateDate();
    }
}
