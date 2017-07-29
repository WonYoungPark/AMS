package io.github.wonyoungpark.ams.common.Exception;

/**
 * Created by One0 on 2017. 7. 29..
 */
public class UserDuplicatedException extends RuntimeException {

    private String username;

    private UserDuplicatedException() {}

    public UserDuplicatedException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
