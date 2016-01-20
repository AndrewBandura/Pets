package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class InvalidPasswordException extends AuthenticationException{
    private final static String DEFAULT_MESSAGE = "Invalid password!";
    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }
}
