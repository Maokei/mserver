package se.maokei.mserver.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import se.maokei.mserver.dto.UserRegisterDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserRegisterDto userRegister = (UserRegisterDto) obj;
        return userRegister.getPassword().equals(userRegister.getMatchingPassword());
    }
}