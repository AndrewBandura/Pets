package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class LoginAttemptsLimitExcessException extends AuthenticationException{
    private final static String DEFAULT_MESSAGE = "Login attempts limit excess!";
    public LoginAttemptsLimitExcessException(String message) {
        super(message);
    }

    public LoginAttemptsLimitExcessException() {
        super(DEFAULT_MESSAGE);
    }
}
