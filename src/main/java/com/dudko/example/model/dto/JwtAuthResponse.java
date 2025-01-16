package com.dudko.example.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "Model of response for the login of existing user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

    @Schema(description = "Secret token")
    @NotNull
    private String accessToken;

}
