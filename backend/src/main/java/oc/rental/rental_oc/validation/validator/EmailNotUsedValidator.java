package oc.rental.rental_oc.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import oc.rental.rental_oc.repository.UserRepository;
import oc.rental.rental_oc.validation.annotation.EmailNotUsed;

public class EmailNotUsedValidator implements ConstraintValidator<EmailNotUsed, String> {

    private final UserRepository userRepository;

    public EmailNotUsedValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        /* Annotation @NotBlank takes care of those cases */
        if (email == null || email.isBlank()) {
            return true;
        }
        return !userRepository.existsByEmail(email);
    }
}
