package api.exceptions;

public class FoodNotFoundException extends Exception {

    public FoodNotFoundException(String message) {
        super(message);
    }
}
