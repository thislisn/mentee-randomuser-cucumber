package framework.exceptions;

public class PropertyReaderException extends RuntimeException {
    public PropertyReaderException(String msg, Throwable e) {
        super(msg, e);
    }
}
