package com.advetofcode.main.year2020.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.advetofcode.main.CustomFileReader;

public class AdapterArray {
    private static CustomFileReader customFileReader = new CustomFileReader();

    private static String path = "/Users/nsj7529/repository/Advent-of-code/Main/src/com/advetofcode/main/resources/Day10_test.txt";

    public static void main(String[] args) throws IOException {
        var rawDataList = customFileReader.readFile(path);

        var inputList = convertToInteger(rawDataList);

        inputList = inputList.stream()
                .sorted()
                .collect(Collectors.toList());


        var current = 0;
        var jolts = new Jolts();

        for (var input : inputList) {
            if (current + 1 == input) {
                jolts.addJolt1();
            }
            if (current + 2 == input) {
                jolts.addJolt2();
            }
            if (current + 3 == input) {
                jolts.addJolt3();
            }
            current = input;
        }

        System.out.println(inputList);
        System.out.println(jolts);
    }

    private static List<Integer> convertToInteger(List<String> inputList) {
        var resultList = new ArrayList<Integer>();
        for (var input : inputList) {
            resultList.add(Integer.parseInt(input));
        }
        return resultList;
    }

    static class Jolts {
        Integer jolt1 = 0;
        Integer jolt2 = 0;
        Integer jolt3 = 1;

        Integer getResult() {
            return jolt1 * jolt3;
        }

        void addJolt1() {
            jolt1++;
        }

        void addJolt2() {
            jolt2++;
        }

        void addJolt3() {
            jolt3++;
        }

        @Override
        public String toString() {
            return String.format("Jolt1: '%s', Jolt2: '%s', Jolt3: '%s', Result: '%s'", jolt1, jolt2, jolt3, getResult());
        }
    }
}
