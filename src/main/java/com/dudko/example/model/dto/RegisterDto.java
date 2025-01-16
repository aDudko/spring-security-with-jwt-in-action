package com.dudko.example.model.dto;

import com.dudko.example.model.validation.UniqueEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Model of request for the register of a new user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @Schema(description = "First and Last name of user")
    @NotNull(message = "{application.constraints.name.NotNull.message}")
    @Size(min = 4, max = 255, message = "{jakarta.validation.constraints.Size.message}")
    private String name;

    @Schema(description = "Email of user")
    @NotNull(message = "{application.constraints.email.NotNull.message}")
    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", message = "{application.constraints.email.Pattern.message}")
    @UniqueEmail
    private String email;

    @Schema(description = "Password of user")
    @NotNull(message = "{application.constraints.password.NotNull.message}")
    @Size(min = 8, max = 255, message = "{jakarta.validation.constraints.Size.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{application.constraints.password.Pattern.message}")
    private String password;

}
