package se.maokei.mserver.dto;

import lombok.Data;
import se.maokei.mserver.validation.PasswordMatches;
import se.maokei.mserver.validation.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserRegisterDto {
    @NotBlank(message = "Password cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Matching password cannot be blank")
    private String matchingPassword;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}
