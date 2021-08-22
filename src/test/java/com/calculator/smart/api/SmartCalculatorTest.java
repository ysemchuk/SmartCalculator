package com.calculator.smart.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SmartCalculatorTest {
    Calculator calculator;
    List<LinkedList<String>> data;
    PrintStream OUT;
    ByteArrayOutputStream outputStream;
    PrintStream stream;

    @Before
    public void init() {
        calculator = new SmartCalculator();
        data = new ArrayList<>();
        OUT = System.out;
        outputStream = new ByteArrayOutputStream();
        stream = new PrintStream(outputStream);
    }

    @Test
    public void givenValidIntNumbers_whenAddition_thenTrue() {
        System.setOut(stream);

        LinkedList<String> validNumbersAddition = new LinkedList<>();
        validNumbersAddition.add("apply 0");
        validNumbersAddition.add("add 3");
        validNumbersAddition.add("add 88");
        validNumbersAddition.add("add 389");
        validNumbersAddition.add("add 1110");
        data.add(validNumbersAddition);
        calculator.calculate(data);

        String number = outputStream.toString().split(" ")[1];
        number = number.replace("\n", "").replace("\r","");
        BigDecimal actualResult = new BigDecimal(number);
        BigDecimal expectedResult = new BigDecimal("1590");

        System.setOut(OUT);
        data.clear();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void givenValidDoubleNumbers_whenMixMathOperations_thenTrue() {
        System.setOut(stream);

        LinkedList<String> validNumbersMix = new LinkedList<>();
        validNumbersMix.add("apply 1.3");
        validNumbersMix.add("add 22.9");
        validNumbersMix.add("division 13.13");
        validNumbersMix.add("sub 202.2");
        validNumbersMix.add("multiply 1333.99");
        data.add(validNumbersMix);
        calculator.calculate(data);

        String number = outputStream.toString().split(" ")[1];
        number = number.replace("\n", "").replace("\r","");
        BigDecimal actualResult = new BigDecimal(number);
        BigDecimal expectedResult = new BigDecimal("-267278.2364");

        System.setOut(OUT);
        data.clear();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test (expected = IllegalArgumentException.class)
    public void givenZeroNumber_whenDivisionByZero_thenException() {
        LinkedList<String> validNumbersMix = new LinkedList<>();
        validNumbersMix.add("apply 6");
        validNumbersMix.add("division 0");
        data.add(validNumbersMix);
        calculator.calculate(data);

        data.clear();
    }
}
