package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class ClientException extends Exception{
    private final static String DEFAULT_MESSAGE = "Client process failed!";
    public ClientException(String message) {
        super(message);
    }

    public ClientException() {
        super(DEFAULT_MESSAGE);
    }
}
