package api.exceptions;

public class EmptyFridgeException extends Exception {

    public EmptyFridgeException(String message) throws EmptyFridgeException {
        super(message);
    }
}

