package hw8.taxi.exception;

/**
 * Created by Andrew on 18.10.2015.
 */
public class AuthorizationException extends AuthenticationException{
    private final static String DEFAULT_MESSAGE = "Authorization process failed!";
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException() {
        super(DEFAULT_MESSAGE);
    }
}
