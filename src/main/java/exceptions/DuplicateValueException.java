package exceptions;

/**
 * Created by Dragoshescu on 01.07.2017.
 */
public class DuplicateValueException extends Exception {

    public DuplicateValueException(Object val){
        super("item of value " + val.toString() + " already present in non-duplicates collection");
    }
}
