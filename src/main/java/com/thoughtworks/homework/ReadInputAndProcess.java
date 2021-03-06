package com.thoughtworks.homework;

import com.thoughtworks.homework.constant.Constant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: guide-to-the-galaxy
 * @description: Class to read and process the data at the same time
 * The processing starts as soon as the input is received
 * ReadInputAndProcess
 * @author: smallsoup
 * @create: 2018-11-15 23:35
 **/

public class ReadInputAndProcess {



    private static Map<String, RomanNumbers> interGalacticRomanMapping = new HashMap<>();
    private static Map<String, Double> objectSoldPerUnitValue = new HashMap<>();

    /**
     * reads and process the input file
     *
     * @param fileName
     */
    public void readFileAndProcess(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                processInput(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Input file not found exception " + e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    /**
     * Parses the input line by line and decides the type of request and appropriately forwards the request
     *
     * @param line
     * @throws Exception
     */
    private void processInput(String line) throws Exception {
        //split by whitespace
        String[] inputs = line.split(" ");
        List<String> inputQuestions = DisplayOutput.inputQuestions;
        List<String> outputValues = DisplayOutput.outputValues;

        if (line.startsWith(Constant.HOW_MANY_CREDITS_IS)) {
            inputQuestions.add(line);
            outputValues.add(String.valueOf(generateCreditValue(Arrays.copyOfRange(inputs, 4, inputs.length - 1))));
        } else if (line.startsWith(Constant.HOW_MUCH_IS)) {
            inputQuestions.add(line);
            outputValues.add(String.valueOf(generateGalacticUnitToNumericValue(Arrays.copyOfRange(inputs, 3, inputs.length - 1))));
        } else if (line.contains(Constant.IS) && !line.contains(Constant.CREDITS)) {
            mapInterGalacticToRomanUnits(inputs);
        } else if (line.contains(Constant.IS) && line.contains(Constant.CREDITS)) {
            generateObjectSoldPerUnitMap(inputs);
        } else {
            inputQuestions.add(line);
            outputValues.add(Constant.NO_IDEA);
        }
    }

    /**
     * Receives inputs of the form "glob is I"
     * maps the alien language to the RomanType
     *
     * @param arr
     */
    private void mapInterGalacticToRomanUnits(String[] arr) {
        try {
            interGalacticRomanMapping.put(arr[0], RomanNumbers.valueOf(arr[2]));
        } catch (IllegalArgumentException e) {
            System.out.println("This type of RomanNumbers is not defined, exiting the program " + e);
        }
    }

    /**
     * Receives input of the form "glob glob Silver is 34 Credits"
     * Creates a map of the object sold and (value/unit)
     * returns -1 in case of a validation error
     *
     * @param arr
     * @return
     * @throws Exception
     */
    private int generateObjectSoldPerUnitMap(String[] arr) throws Exception {
        StringBuilder romanNumeral = new StringBuilder();
        int i;
        for (i = 0; i < arr.length; i++) {
            RomanNumbers romanNumbers = interGalacticRomanMapping.get(arr[i]);
            if (romanNumbers != null) {
                romanNumeral.append(romanNumbers.getDisplayValue());
            } else {
                break;
            }
        }

        //example romanNumbers is MMVI
        int value = RomanValidator.validateRoman(romanNumeral.toString());
        if (value == -1) {
            return -1;
        }
        String objectName = arr[i];
        int credit = Integer.parseInt(arr[i + 2]);

        objectSoldPerUnitValue.put(objectName, (double) credit / value);
        return 1;
    }

    /**
     * Receives input of the form "pish tegj glob glob" for questions like "how much is pish tegj glob glob ?"
     * returns -1 in case of validation exception
     *
     * @param arr
     * @return
     * @throws Exception
     */
    private int generateGalacticUnitToNumericValue(String[] arr) {

        try {
            String romanNumeral = generateRomanFromGalacticUnit(arr);

            if (romanNumeral == null) {
                return -1;
            }

            return RomanValidator.validateRoman(romanNumeral);
        } catch (Exception e) {
            return -1;
        }

    }


    /**
     * Receives input of the form "glob prok Silver" for questions like "how many Credits is glob prok Silver ?"
     * returns -1 in case of validation exception
     *
     * @param arr
     * @return
     * @throws Exception
     */
    private Double generateCreditValue(String[] arr) {
        int currentValue = generateGalacticUnitToNumericValue(Arrays.copyOfRange(arr, 0, arr.length - 1));

        if (currentValue == -1) {
            return (double) -1;
        }

        return currentValue * objectSoldPerUnitValue.get(arr[arr.length - 1]);
    }

    /**
     * a utils method to generate RomanNumbers string from Alien input
     *
     * @param arr
     * @return
     */
    private String generateRomanFromGalacticUnit(String[] arr) {
        StringBuilder romanNumeral = new StringBuilder();
        int i;
        for (i = 0; i < arr.length; i++) {
            RomanNumbers romanNumbers = interGalacticRomanMapping.get(arr[i]);
            if (romanNumbers != null) {
                romanNumeral.append(romanNumbers.getDisplayValue());
            } else {
                break;
            }
        }

        if (i != arr.length) {
            return null;
        }

        return romanNumeral.toString();
    }

}