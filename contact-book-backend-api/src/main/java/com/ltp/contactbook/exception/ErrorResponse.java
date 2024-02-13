package com.ltp.contactbook.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Map<String, List<String>> validationErrors;

    public ErrorResponse(Map<String, List<String>> validationErrors) {
        this.timestamp = LocalDateTime.now();
        this.validationErrors = validationErrors;
    }


}
