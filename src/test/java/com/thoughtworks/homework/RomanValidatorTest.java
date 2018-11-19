package com.thoughtworks.homework;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: guide-to-the-galaxy
 * @description: RomanValidatorTest
 * @author: smallsoup
 * @create: 2018-11-15 23:37
 **/

public class RomanValidatorTest {
    @Test
    public void testValidateRoman() throws Exception {
        Assert.assertEquals(2006 ,RomanValidator.validateRoman("MMVI"));
        Assert.assertEquals(1944 ,RomanValidator.validateRoman("MCMXLIV"));
        Assert.assertEquals(1000 ,RomanValidator.validateRoman("M"));
        Assert.assertEquals(900 ,RomanValidator.validateRoman("CM"));
        Assert.assertEquals(3 ,RomanValidator.validateRoman("III"));
        Assert.assertEquals(1903 ,RomanValidator.validateRoman("MCMIII"));
    }
}
