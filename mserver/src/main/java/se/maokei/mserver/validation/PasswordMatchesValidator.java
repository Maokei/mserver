package se.maokei.mserver.validation;

import se.maokei.mserver.dto.UserRegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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