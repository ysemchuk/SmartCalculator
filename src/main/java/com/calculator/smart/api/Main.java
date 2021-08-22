package com.calculator.smart.api;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];
        List<LinkedList<String>> data = DataReader.readFromFile(fileName);
        Calculator calculator = new SmartCalculator();
        calculator.calculate(data);
    }
}
