package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class AuthenticationException extends Exception{
    private final static String DEFAULT_MESSAGE = "Login process failed!";
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super(DEFAULT_MESSAGE);
    }
}
