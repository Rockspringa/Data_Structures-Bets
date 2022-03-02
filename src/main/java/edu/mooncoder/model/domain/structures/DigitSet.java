package edu.mooncoder.model.domain.structures;

import edu.mooncoder.model.tools.exceptions.NotADigitException;
import edu.mooncoder.model.tools.exceptions.RepeatedDigitExpection;

public class DigitSet {
    private int[] digits = new int[10];

    public void add(int digit) throws NotADigitException, RepeatedDigitExpection {
        if (digit < 0 && 9 < digit)
            throw new NotADigitException(digit); 

        if (digits[digit] != digit + 1)
            digits[digit] = digit + 1;
        else
            throw new RepeatedDigitExpection(digit);
    }
}
