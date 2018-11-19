package com.thoughtworks.homework;

import com.thoughtworks.homework.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guide-to-the-galaxy
 * @description: DisplayOutput
 * @author: smallsoup
 * @create: 2018-11-15 23:38
 **/

public class DisplayOutput {

    private DisplayOutput() {

    }

    /**
     * these two lists hold question asked and out for same indexes
     * this will hold all the questions asked in the input
     */
    public static final List<String> inputQuestions = new ArrayList<>();

    /**
     * this holds the output of the questions and special values if the question was not valid or a validation failure occurs
     */
    public static final List<String> outputValues = new ArrayList<>();



    /**
     * Display the output based on the lists
     */
    public static void processOutput() {

        //input nums equals output nums
        for (int i = 0; i < inputQuestions.size(); i++) {

            //result to console
            StringBuilder result = new StringBuilder();

            String question = inputQuestions.get(i);
            String output = outputValues.get(i);

            if (output == Constant.NO_IDEA || Double.valueOf(output).intValue() == -1) {
                result.append(Constant.NO_IDEA);
            } else {
                if (question.startsWith(Constant.HOW_MUCH_IS)) {
                    result.append(question.substring(Constant.HOW_MUCH_IS.length(), question.length() - 1).trim());
                    result.append(Constant.IS);
                    result.append(Double.valueOf(output).intValue());
                } else if (question.startsWith(Constant.HOW_MANY_CREDITS_IS)) {
                    result.append(question.substring(Constant.HOW_MANY_CREDITS_IS.length(), question.length() - 1).trim());
                    result.append(Constant.IS);
                    result.append(Double.valueOf(output).intValue());
                    result.append(Constant.CREDITS);
                } else {
                    result.append(Constant.NO_IDEA);
                }
            }

            System.out.println(result.toString());
        }
    }

}
