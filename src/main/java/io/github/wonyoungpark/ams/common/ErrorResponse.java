package io.github.wonyoungpark.ams.common;

import lombok.Data;

import java.util.List;

/**
 * Created by One0 on 2017. 7. 29..
 */
@Data
public class ErrorResponse {
    private String message;
    private String code;
    private List<FieldError> errors;

    public static class FieldError {
        private String field;
        private String value;
        private String reason;
    }
}
