package com.calculator.smart.api;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*String fileName = args[0];
        List<LinkedList<String>> data = DataReader.readFromFile(fileName);
        Calculator calculator = new SmartCalculator();
        calculator.calculate(data);*/
        BigDecimal bd = BigDecimal.ZERO;
        System.out.println(bd.equals(new BigDecimal("0.0")));
    }
}
