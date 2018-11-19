package com.thoughtworks.homework;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.Mocked;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @program: guide-to-the-galaxy
 * @description: ReadInputAndProcessTest
 * @author: smallsoup
 * @create: 2018-11-19 21:52
 **/

public class ReadInputAndProcessTest {

//    @Mocked
    ReadInputAndProcess process = new ReadInputAndProcess();


    @Test
    public void testReadFileAndProcess() throws Exception {
        process.readFileAndProcess("input.txt");

        Class<? extends ReadInputAndProcess> clazz = process.getClass();

        Field field = clazz.getDeclaredField("interGalacticRomanMapping");

        field.setAccessible(true);

        Map<String, RomanNumbers> interGalacticRomanMapping = (Map) field.get(process);

//        Map<String, RomanNumbers> interGalacticRomanMapping = Deencapsulation.getField(process.getClass(), "interGalacticRomanMapping");

        System.out.println(interGalacticRomanMapping);
    }

}
