package io.github.wonyoungpark.ams.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by One0 on 2017. 7. 28..
 */
public class UserDTO {
    @Data
    public static class Create {
        @NotBlank
        @Size(min = 5)
        private String username;

        @NotBlank
        @Size(min = 5)
        private String password;
    }

    @Data
    public static class Response {
        private Long id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private Date joined;
    }

    @Data
    public static class Update {
        @NotBlank
        @Size(min = 5)
        private String password;
        private String email;
        private String firstName;
        private String lastName;
    }
}
