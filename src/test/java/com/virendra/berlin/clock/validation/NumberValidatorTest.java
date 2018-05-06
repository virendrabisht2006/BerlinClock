package com.virendra.berlin.clock.validation;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Virendra Singh on 06/05/18.
 */
public class NumberValidatorTest {

    @Test
    public void shouldOnlyContainNumberAsInput() {
        String input = "1234";
        Assert.assertEquals(true, NumberValidator.validate(input));
        Assert.assertEquals(false, NumberValidator.validate(" xyz"));
    }

    @Test
    public void inputNumberShouldBeBetweenZeroAndFiftyNine() {
        Assert.assertEquals(true, NumberValidator.numberRangeCheck("000"));
        Assert.assertEquals(true, NumberValidator.numberRangeCheck("59"));
        Assert.assertEquals(false, NumberValidator.numberRangeCheck("-1"));
        Assert.assertEquals(false, NumberValidator.numberRangeCheck("126362"));
    }
}