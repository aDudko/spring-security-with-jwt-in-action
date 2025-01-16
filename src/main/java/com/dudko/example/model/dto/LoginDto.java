package com.dudko.example.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Model of request for the login of existing user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Schema(description = "Email of user")
    @NotNull
    private String email;

    @Schema(description = "Password of user")
    @NotNull
    private String password;

}
