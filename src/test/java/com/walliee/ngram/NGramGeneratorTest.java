package com.walliee.ngram;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NGramGeneratorTest {

    @Test
    public void test3GramGenerator() {
        List<String> actualNGrams = new NGramGenerator().generateNGrams("awarding", 3);
        List<String> expectedNGrams = Arrays.asList("awa", "war", "ard", "rdi", "din", "ing");
        Assert.assertEquals(expectedNGrams, actualNGrams);
    }

    @Test
    public void test4GramGenerator() {
        List<String> actualNGrams = new NGramGenerator().generateNGrams("awarding", 4);
        List<String> expectedNGrams = Arrays.asList("awar", "ward", "ardi", "rdin", "ding");
        Assert.assertEquals(expectedNGrams, actualNGrams);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureOnNull() {
        new NGramGenerator().generateNGrams(null, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureOnEmptyInput() {
        new NGramGenerator().generateNGrams("", 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureOnIllegalSize() {
        new NGramGenerator().generateNGrams("awarding", -1);
    }
}