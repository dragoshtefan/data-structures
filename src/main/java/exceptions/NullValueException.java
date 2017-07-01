package exceptions;

/**
 * Created by Dragoshescu on 01.07.2017.
 */
public class NullValueException extends Exception{

    public NullValueException() {
        super("null value encountered");
    }
}
