package se.maokei.mserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import se.maokei.mserver.validation.PasswordMatches;
import se.maokei.mserver.validation.ValidEmail;

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
