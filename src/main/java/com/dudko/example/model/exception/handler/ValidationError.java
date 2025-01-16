package com.dudko.example.model.exception.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Model of response in case of an validation error")
@Data
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationError {

    @Schema(description = "Error occurred date time")
    private LocalDateTime timestamp;

    @Schema(description = "Error status")
    private int status;

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "Error url")
    private String url;

    @Schema(description = "Map of validation errors")
    private Map<String, String> validationErrors;

    public ValidationError(int status, String message, String url) {
        super();
        this.status = status;
        this.message = message;
        this.url = url;
    }

}
