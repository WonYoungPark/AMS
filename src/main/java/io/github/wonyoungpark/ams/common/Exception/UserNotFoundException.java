package io.github.wonyoungpark.ams.common.Exception;

/**
 * Created by One0 on 2017. 7. 29..
 */
public class UserNotFoundException extends RuntimeException {

    private Long id;

    private UserNotFoundException() {}

    public UserNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
