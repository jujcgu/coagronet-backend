package com.coagronet.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private Long personaId;
    private Long estadoId;
}
