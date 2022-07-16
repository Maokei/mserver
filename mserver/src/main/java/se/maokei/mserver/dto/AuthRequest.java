package se.maokei.mserver.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class  AuthRequest {
  @NotBlank(message = "Username cannot be blank")
  private String username;
  @NotBlank(message = "Password cannot be blank")
  private String password;
}
