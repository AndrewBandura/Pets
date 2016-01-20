package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class UserBlockedException extends AuthenticationException{
    private final static String DEFAULT_MESSAGE = "Your account is locked!";
    public UserBlockedException(String message) {
        super(message);
    }

    public UserBlockedException() {
        super(DEFAULT_MESSAGE);
    }
}
