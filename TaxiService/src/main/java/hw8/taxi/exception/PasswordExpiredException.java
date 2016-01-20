package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class PasswordExpiredException extends AuthenticationException{
    private final static String DEFAULT_MESSAGE = "Your password is expired!";
    public PasswordExpiredException(String message) {
        super(message);
    }

    public PasswordExpiredException() {
        super(DEFAULT_MESSAGE);
    }
}
