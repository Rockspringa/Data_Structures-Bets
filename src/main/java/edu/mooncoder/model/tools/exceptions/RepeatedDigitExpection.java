package edu.mooncoder.model.tools.exceptions;

public class RepeatedDigitExpection extends Exception {
    public RepeatedDigitExpection(int num) {
        super(String.format("Ya se habia apostado por el caballo no. %d.", num + 1));
    }
}
