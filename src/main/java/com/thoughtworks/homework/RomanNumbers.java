package com.thoughtworks.homework;

/**
 * @program: guide-to-the-galaxy
 * @description: RomanNumbers
 * @author: smallsoup
 * @create: 2018-11-15 23:37
 **/

public enum RomanNumbers {

    /**
     * Symbol Value
     * I        1
     */
    I("I", 1),

    /**
     * Symbol Value
     * V        5
     */
    V("V", 5),

    /**
     * Symbol Value
     * X        10
     */
    X("X", 10),

    /**
     * Symbol Value
     * L       50
     */
    L("L", 50),

    /**
     * Symbol Value
     * C       100
     */
    C("C", 100),

    /**
     * Symbol Value
     * D       500
     */
    D("D", 500),

    /**
     * Symbol Value
     * M       1000
     */
    M("M", 1000);

    private int value;
    private String displayValue;

    RomanNumbers(String displayValue, int value) {
        this.displayValue = displayValue;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
