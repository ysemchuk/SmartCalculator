package com.calculator.smart.api;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class SmartCalculator implements Calculator {
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final BigDecimal bdOneZero = new BigDecimal("0.0");
    private static final BigDecimal bdTwoZero = new BigDecimal("0.00");

    public void calculate(List<LinkedList<String>> data) {
        for (List<String> oneCalculation : data) {
            BigDecimal result = BigDecimal.ZERO;
            for (String line : oneCalculation) {
                String[] splittedLine = line.split(" ");
                BigDecimal bdNumber =
                        BigDecimal.valueOf(Double.parseDouble(splittedLine[SECOND_ELEMENT]));
                switch (splittedLine[FIRST_ELEMENT]) {
                    case "add":
                        result = addition(result, bdNumber);
                        break;
                    case "multiply":
                        result = multiplication(result, bdNumber);
                        break;
                    case "sub":
                        result = subtraction(result, bdNumber);
                        break;
                    case "division":
                        result = division(result, bdNumber);
                        break;
                    case "apply":
                        result = bdNumber;
                        break;
                    default:
                        log.debug("I don't know such an mathematical operation");
                }
            }
            BigDecimal fractionalPart = result.remainder(BigDecimal.ONE);
            if (isZero(fractionalPart)) {
                System.out.println("Output: " + result.setScale(0, RoundingMode.UP));
            } else {
                System.out.println("Output: " + result);
            }
        }
    }

    private BigDecimal addition(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2);
    }

    private BigDecimal subtraction(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2);
    }

    private BigDecimal multiplication(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2);
    }

    private BigDecimal division(BigDecimal number1, BigDecimal number2) {
        if (isZero(number2)) {
            log.error("Division by zero");
            throw new IllegalArgumentException("Argument number2 is 0");
        }
        return number1.divide(number2, 2, RoundingMode.HALF_DOWN);
    }

    private boolean isZero(BigDecimal number) {
        return bdOneZero.equals(number) || bdTwoZero.equals(number) || BigDecimal.ZERO.equals(number);
    }
}
