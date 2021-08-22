package com.calculator.smart.api;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Slf4j
public class DataReader {
    private static final String APPLY = "apply";

    private DataReader() {
    }

    public static List<LinkedList<String>> readFromFile(String fileName) {
        List<LinkedList<String>> data = new ArrayList<>();
        LinkedList<String> oneCalculation = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(APPLY)) {
                    oneCalculation.addFirst(line);
                    data.add(new LinkedList<>(oneCalculation));
                    oneCalculation.clear();
                } else {
                    oneCalculation.add(line);
                }
            }
        } catch (IOException ex) {
            log.debug("File read error", ex);
        }
        return data;
    }
}
