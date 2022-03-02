package edu.mooncoder.model.tools.exceptions;

public class NotADigitException extends Exception {
    public NotADigitException(int num) {
        super(String.format("El caballo no. %d no existe, solo existen del 1 al 10.", num + 1));
    }
}
