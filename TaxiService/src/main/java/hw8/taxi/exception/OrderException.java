package hw8.taxi.exception;

/**
 * Created by Andrew on 02.10.2015.
 */
public class OrderException extends Exception{
    private final static String DEFAULT_MESSAGE = "Order process failed!";
    public OrderException(String message) {
        super(message);
    }

    public OrderException() {
        super(DEFAULT_MESSAGE);
    }
}
