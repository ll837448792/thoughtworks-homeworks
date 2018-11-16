package com.thoughtworks.homework;

/**
 * @program: guide-to-the-galaxy
 * @description: RomanNumbers
 * @author: smallsoup
 * @create: 2018-11-15 23:37
 **/

public enum RomanNumbers {
    i("i", 1), v("v", 5), x("x", 10), l("l", 50), c("c", 100), d("d", 500), m("m", 1000);

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
