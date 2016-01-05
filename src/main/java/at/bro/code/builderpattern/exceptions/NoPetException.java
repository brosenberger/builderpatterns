package at.bro.code.builderpattern.exceptions;

public class NoPetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoPetException(String message) {
        super(message);
    }

}
