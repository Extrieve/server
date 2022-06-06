package io.getarrays.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    protected LocalDateTime timestamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String errorMessage;
    protected String successMessage;
    protected String developerMessage;
    protected Map <?, ?> data;
}
