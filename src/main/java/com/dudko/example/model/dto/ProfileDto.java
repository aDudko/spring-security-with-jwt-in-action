package com.dudko.example.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Model of response for getting profile about an existing user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    @Schema(description = "User ID")
    @NotNull
    private Long id;

    @Schema(description = "First and Last name of user")
    @NotNull
    private String name;

    @Schema(description = "Email of user")
    @NotNull
    private String email;

    @Schema(description = "Hash of password for user")
    @NotNull
    private String passwordHash;

}
